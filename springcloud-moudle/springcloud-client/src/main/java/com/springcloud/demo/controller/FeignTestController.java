package com.springcloud.demo.controller;

import com.springcloud.demo.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-14 10:11
 */
@RestController
@RequestMapping("/test/feign")
public class FeignTestController {

    @GetMapping()
    public R getUser(){
        Map result = new HashMap();
        result.put("name", "xiaoming");
        result.put("sex", "male");
        result.put("age", 12);
        return R.suc(result);
    }
}
