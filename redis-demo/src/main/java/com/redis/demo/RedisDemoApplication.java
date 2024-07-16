package com.redis.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@MapperScan("com.redis.demo.test2.mapper")
@SpringBootApplication
public class RedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

  /*  @Bean
    public Redisson redisson(){

        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.219.88:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
*/
}
