package com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-21 17:27
 */
@EnableScheduling //开启定时任务
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudJobApplication.class,args);
    }
}
