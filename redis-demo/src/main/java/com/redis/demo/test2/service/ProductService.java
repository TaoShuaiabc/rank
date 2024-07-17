package com.redis.demo.test2.service;

import com.alibaba.fastjson.JSON;
import com.redis.demo.test2.common.RedisKeyPrefixConst;
import com.redis.demo.test2.common.RedisUtil;
import com.redis.demo.test2.entity.Product;
import com.redis.demo.test2.mapper.ProductMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private Redisson redisson;


    public static final Integer PRODUCT_CACHE_TIMEOUT = 60 * 60 * 24;
    private static final String EMPTY_CATCH = "{}";
    private static final String LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX = "lock:product:hot_cache_create:";
    private static final String LOCK_PRODUCT_UPDATE_PREFIX = "lock:product:update:";


    public void set(Product product) {

        //这里加锁主要为了解决双写不一致的问题
        RReadWriteLock productUpdateLock = redisson.getReadWriteLock(LOCK_PRODUCT_UPDATE_PREFIX + product.getProductId());
        RLock writeLock = productUpdateLock.writeLock();
        writeLock.lock();
        try {
            Product productResult = productMapper.set(product);
            redisUtil.set(RedisKeyPrefixConst.PRODUCT_CACHE+productResult.getProductId(),JSON.toJSON(productResult),genProductCacheTimeout(),TimeUnit.SECONDS);
        }finally {
            writeLock.unlock();
        }
    }

    public void update(Product product) {

        //这里加锁主要为了解决双写不一致的问题
        RReadWriteLock productUpdateLock = redisson.getReadWriteLock(LOCK_PRODUCT_UPDATE_PREFIX + product.getProductId());
        RLock writeLock = productUpdateLock.writeLock();
        writeLock.lock();
        try {
            Product productResult = productMapper.update(product);
            redisUtil.set(RedisKeyPrefixConst.PRODUCT_CACHE+productResult.getProductId(),JSON.toJSON(productResult),genProductCacheTimeout(),TimeUnit.SECONDS);
        }finally {
            writeLock.unlock();
        }
    }

    public Product get(Long productId) {

        Product product = null;

        String productCatchKey = RedisKeyPrefixConst.PRODUCT_CACHE + productId;

        //查询缓存
        //这个方法的代码看起来很长，但是实际开发中99.99%的情况都是执行到此处就返回
        product = getProductFromCache(productCatchKey, product);
        if (product != null) {
            return product;
        }

        //加锁防止热点缓存并发重建问题，即某个不在缓存中的冷门商品突然火爆，导致大量请求打到数据库
        RLock lock = redisson.getLock(LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX + productId);
        //lock.tryLock(1,TimeUnit.SECONDS); 这里可以使用tryLock进行优化，前面代码的意思是如果被加锁的业务在一秒钟内能执行完毕的话，
        // 可以使用这个，让锁一秒钟后失效，这样就可以让串行转并发了，当然要考虑业务是否能在一秒钟之内处理完毕，如果没有处理完毕，则会有并发问题
        lock.lock();
        try {
            //查询缓存 双重检测机制,也叫DCL(double check lock) 主要用于已经在排队中的线程提升效率，
            // 例如线程一查完数据库，并设置缓存，然后解锁，此时已经在排队的线程二拿到锁，发现缓存中已存在，则直接返回。
            product = getProductFromCache(productCatchKey, product);
            if (product != null) {
                return product;
            }

            //这里加锁主要为了解决双写不一致的问题
            RReadWriteLock productUpdateLock = redisson.getReadWriteLock(LOCK_PRODUCT_UPDATE_PREFIX + productId);
            //使用读锁
            RLock readLock = productUpdateLock.readLock();
            readLock.lock();
            try {
                //查询数据库
                product = productMapper.getProduct(productId);
                if (product != null) {
                    redisUtil.set(productCatchKey, JSON.toJSONString(product), genProductCacheTimeout(), TimeUnit.SECONDS);
                } else {
                    //防止缓存穿透,当查询数据库不存在的商品时，往缓存存入一个空值，防止请求一直打到数据库。另外一定要设置过期时间，防止缓存中出现大量空值
                    //当然最好还是使用布隆过滤器
                    redisUtil.set(productCatchKey, EMPTY_CATCH, genEmptyCacheTimeout(), TimeUnit.SECONDS);
                }
            }finally {
                readLock.unlock();
            }
        } finally {
            lock.unlock();
        }

        return product;
    }



    /**
     * 返回随机过期时间，防止缓存击穿
     */
    private Integer genProductCacheTimeout() {
        return PRODUCT_CACHE_TIMEOUT + new Random().nextInt(5) * 60 * 60;
    }

    private Integer genEmptyCacheTimeout() {
        return 60 + new Random().nextInt(30);
    }

    /**
     * 从缓存中获取商品
     */
    private Product getProductFromCache(String productCatchKey,Product product) {

        String productStr = redisUtil.get(productCatchKey);
        if (!StringUtils.isEmpty(productStr)) {
            if (EMPTY_CATCH.equals(productCatchKey)) {
                //如果是空值，也是要设置读延期，防止一直用同一个不存在的key进行攻击
                redisUtil.expire(productCatchKey, genEmptyCacheTimeout(), TimeUnit.SECONDS);
                //这里返回的是一个空对象，要告知前端这种空对象是为了防止黑客攻击，让前端做区分
                return new Product();
            }
            product = JSON.parseObject(productStr, Product.class);
            //读延期，让热点数据一直保存在缓存中，冷数据过期。简单的冷热分离
            redisUtil.expire(productCatchKey, genProductCacheTimeout(), TimeUnit.SECONDS);
        }
        return product;
    }
}
