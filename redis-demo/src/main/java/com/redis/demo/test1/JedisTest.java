package com.redis.demo.test1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class JedisTest {

    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(20);

        jedisPoolConfig.setMaxIdle(10);

        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.219.88", 6379, 3000, null);

        Jedis jedis = null;


        jedis = jedisPool.getResource();

             /*System.out.println(jedis.set("single", "zhuge"));

            System.out.println(jedis.get("single"));*/

        Pipeline pl = jedis.pipelined();
        for (int i = 0; i < 10; i++) {
            pl.incr("pipelineKey");
            pl.set("name" + 1, "taoshuai");
            pl.setbit("age",-1,true);
        }
        List<Object> objects = pl.syncAndReturnAll();
        System.out.println(objects);


    }
}