package com.redis.demo;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JedisSentinelTestController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


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
}
