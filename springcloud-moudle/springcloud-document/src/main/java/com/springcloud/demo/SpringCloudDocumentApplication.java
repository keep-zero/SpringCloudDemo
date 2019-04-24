package com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-04-22 17:00
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDocumentApplication.class, args);
    }

}
