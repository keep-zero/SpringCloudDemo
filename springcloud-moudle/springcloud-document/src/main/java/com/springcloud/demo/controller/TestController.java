package com.springcloud.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

//    @Value("${test.user.name}")
//    private String username;
//
//    @Value("${test.user.age}")
//    private Integer age;
//
//    @RequestMapping("/getUser")
//    public String getUser(){
//        return "username:"+username+";age:"+age;
//    }

}
