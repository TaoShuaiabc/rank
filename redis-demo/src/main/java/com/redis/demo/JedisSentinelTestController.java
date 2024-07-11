package com.redis.demo;


import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JedisSentinelTestController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Redisson redisson;


    @RequestMapping("/test_sentinel")
    public void testSentinel()  {

        int i = 1;
        while (true){
            try {
                stringRedisTemplate.opsForValue().set("liangzai"+i, i+"");
                System.out.println(" key： "+ "liangzai" + i);
                        i++;
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @RequestMapping("/cluster")
    public void testCluster() throws InterruptedException {
        stringRedisTemplate.opsForValue().set("stock", "300");
        System.out.println(stringRedisTemplate.opsForValue().get("stock"));

    }

    //分布式锁测试
    @RequestMapping("/key")
    public String key(){
        RLock lock = redisson.getLock("1011");
        lock.lock();
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock>0){
            int realStock = stock - 1 ;
            stringRedisTemplate.opsForValue().set("stock",realStock+"");
            System.out.println(realStock);
        }else {
            System.out.println("扣减库存失败");
        }
        lock.unlock();
        return "200";
    }


}
