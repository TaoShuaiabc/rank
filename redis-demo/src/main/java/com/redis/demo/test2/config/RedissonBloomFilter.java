package com.redis.demo.test2.config;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 布隆过滤器
 */
public class RedissonBloomFilter {

    public static void main(String[] args) {


        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.219.88:6379").setDatabase(0);

        RedissonClient redissonClient = Redisson.create(config);

        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("nameList");

        //预计有1亿个元素存放在redis中，误差率为0.03,它会根据这个计算出所需要的bit数组长度
        bloomFilter.tryInit(100000000L,0.03);
        //将元素放入布隆过滤器
        bloomFilter.add("taoshuai");

        //判断以下元素是否在redis中
        System.out.println(bloomFilter.contains("jiangli"));
        System.out.println(bloomFilter.contains("taoyifan"));
        System.out.println(bloomFilter.contains("taoshuai"));

    }
}
