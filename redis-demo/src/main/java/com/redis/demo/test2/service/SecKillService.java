package com.redis.demo.test2.service;

import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 *  秒杀测试
 */
@Service
public class SecKillService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private Redisson redisson;

    public static String SEC_KILL_SKU = "sec:kill:sku";
    public static String SEC_KILL_SKU_LOCK = "sec:kill:sku:lock";

    public String secKill() {


        //RLock lock = redisson.getLock(SEC_KILL_SKU_LOCK);
        //lock.lock();
        //拿到库存
        Integer in = (Integer) redisTemplate.opsForValue().get(SEC_KILL_SKU);

        if (ObjectUtils.isEmpty(in)) {
            return "该商品不存在！";
        }
        if (in <= 0) {
            return "商品已抢完！";
        }
        //判断库存是否大于0
        //大于则扣减，否则不处理
        redisTemplate.opsForValue().set(SEC_KILL_SKU, in - 1);

        System.out.println(Thread.currentThread().getName());
        return "完成";
    }
}
