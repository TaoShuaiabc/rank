package com.redis.demo.test2.mapper;

import com.redis.demo.test2.entity.Product;

public interface ProductMapper {


    Product getProduct(Long id);

    Product update(Product product);

    Product set(Product product);
}