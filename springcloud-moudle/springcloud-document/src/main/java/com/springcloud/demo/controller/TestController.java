package com.springcloud.demo.controller;

import cn.hutool.json.JSONUtil;
import com.springcloud.demo.helper.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisHelper redisHelper;

    @PostMapping("/addKey")
    public String addKey(String key,String value){
        return JSONUtil.toJsonStr(redisHelper.set(key, value));
    }


    @GetMapping("/getValue")
    public String getValue(String key){
        return JSONUtil.toJsonStr(redisHelper.get(key));
    }

    @PostMapping("/addSet")
    public String addSet(String key,String value1,String value2){
        return JSONUtil.toJsonStr(redisHelper.sSet(key, value1,value2));
    }

    @GetMapping("/getSet")
    public String getSet(String key){
        return JSONUtil.toJsonStr(redisHelper.sGet(key));
    }

    @PostMapping("/addHash")
    public String addHash(String key,String item,String value){
        return JSONUtil.toJsonStr(redisHelper.hset(key, item, value));
    }

    @GetMapping("/getHash")
    public String getHash(String key,String item){
        return JSONUtil.toJsonStr(redisHelper.hget(key,item));
    }



}
