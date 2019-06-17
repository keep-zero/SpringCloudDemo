package com.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-05-15 0:28
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
public class SpringCloudClient2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClient2Application.class, args);
    }

}
