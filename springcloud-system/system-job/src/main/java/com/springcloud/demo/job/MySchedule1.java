package com.springcloud.demo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description 测试定时类
 * @auther: lai.guanfu
 * @date: 2019-06-26 16:15
 */
@Slf4j
@Component
public class MySchedule1 {

    public void test(){
        if(log.isDebugEnabled()){
            log.debug("测试定时");
        }
    }
}
