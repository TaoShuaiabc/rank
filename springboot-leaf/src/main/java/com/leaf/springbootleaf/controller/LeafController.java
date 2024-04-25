package com.leaf.springbootleaf.controller;


import com.leaf.springbootleaf.common.Result;
import com.leaf.springbootleaf.common.Status;
import com.leaf.springbootleaf.service.SnowflakeService;
import com.leaf.springbootleaf.snowflake.exception.LeafServerException;
import com.leaf.springbootleaf.snowflake.exception.NoKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeafController {

    @Autowired
    private SnowflakeService snowflakeService;


    @RequestMapping(value = "/api/snowflake/get/{key}")
    public String getSnowflakeId(@PathVariable("key") String key) {
        return get(key, snowflakeService.getId(key));
    }

    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }
}
