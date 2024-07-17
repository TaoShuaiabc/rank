package com.redis.demo.test2.controller;

import com.redis.demo.test2.entity.Product;
import com.redis.demo.test2.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Resource
    private ProductService productService;

    @GetMapping("set")
    private void set(){
        productService.set(new Product());
    }

    @GetMapping("get")
    private Product get(){
       return productService.get(1L);
    }

    @GetMapping("update")
    private void update(){
        productService.update(new Product());
    }
}
