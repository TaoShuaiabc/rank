package com.example.seckill;

import com.alibaba.fastjson.JSON;
import com.zhuge.Application;
import com.zhuge.common.RedisKeyPrefixConst;
import com.zhuge.common.RedisUtil;
import com.zhuge.model.Product;
import com.zhuge.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
public class ApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate; //1
    @Autowired
    RedisUtil redisUtil; //1
    @Autowired
    ProductService productService; //1

    @Test
    public void test() throws Exception {
        //设置一个key，商品的库存数量为10
        redisUtil.set(RedisKeyPrefixConst.PRODUCT_CACHE + 1,
                "{}");

        Product productResult = new Product();
        productResult.setId(2L);
        productResult.setName("test");
        redisUtil.set(RedisKeyPrefixConst.PRODUCT_CACHE + productResult.getId(),
                JSON.toJSONString(productResult));

        System.out.println(redisUtil.get(RedisKeyPrefixConst.PRODUCT_CACHE + 2));
        Product product = JSON.parseObject(redisUtil.get(RedisKeyPrefixConst.PRODUCT_CACHE + 2), Product.class);
        System.out.println("product---" + product);

        System.out.println(redisUtil.get(RedisKeyPrefixConst.PRODUCT_CACHE + 1));

        stringRedisTemplate.opsForValue().set("stock", "10");
        Assert.assertEquals("10", stringRedisTemplate.opsForValue().get("stock"));
    }

    @Test
    public void test2() throws Exception {
        Product product = new Product();
        product.setId(6L);
        product.setName("test6");
        productService.update(product);
        System.out.println(productService.get(6L));
    }

}
