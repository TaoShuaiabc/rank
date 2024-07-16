package com.redis.demo.test2.service;

import com.redis.demo.test2.entity.Product;
import com.redis.demo.test2.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;


    public void set() {
    }

    public Product get() {
       return productMapper.getProduct(1L);
    }

    public void update() {
    }
}
