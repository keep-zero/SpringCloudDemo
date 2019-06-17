package com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-04 15:59
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudOAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOAuthApplication.class, args);
    }
}
