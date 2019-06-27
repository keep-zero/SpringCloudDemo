package com.springcloud.demo.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-25 14:11
 */
@Slf4j
public class MyTestRunable implements Runnable {
    @Override
    public void run() {
      if(log.isDebugEnabled()){
          log.debug("测试内容");
      }
    }
}
