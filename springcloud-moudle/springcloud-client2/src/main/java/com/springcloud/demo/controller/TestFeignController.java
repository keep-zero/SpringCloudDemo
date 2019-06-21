package com.springcloud.demo.controller;

import com.springcloud.demo.feign.TestFeignIntercept;
import com.springcloud.demo.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-14 11:42
 */
@RestController
@RequestMapping("/testFeign")
public class TestFeignController {
    @Autowired
    private TestFeignIntercept testFeignIntercept;

    /**
     * 获取信息
     * @return
     */
    @GetMapping
    public R getFeignMessage(){
        return this.testFeignIntercept.getFeignMassage();
    }
}
