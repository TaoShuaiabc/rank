package com.redis.demo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class JedisSentinelTest {

    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(20);

        jedisPoolConfig.setMaxIdle(10);

        jedisPoolConfig.setMinIdle(5);


        String masterName = "mymaster";

        Set<String> sentinels = new HashSet<String>();
        sentinels.add(new
                HostAndPort("192.168.219.88",26379).toString());
        sentinels.add(new
                HostAndPort("192.168.219.88",26380).toString());
        sentinels.add(new
                HostAndPort("192.168.219.88",26381).toString());

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig, 3000, null);
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            System.out.println(jedis.set("sentinel", "taoshuai111"));
            System.out.println(jedis.get("sentinel"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
            jedis.close();
    }}
}
