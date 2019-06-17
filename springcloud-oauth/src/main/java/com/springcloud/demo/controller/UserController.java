package com.springcloud.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @description 用户认证
 * @auther: lai.guanfu
 * @date: 2019-06-05 11:59
 */
@RestController
@Slf4j
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
