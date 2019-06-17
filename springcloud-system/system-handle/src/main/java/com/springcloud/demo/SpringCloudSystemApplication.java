package com.springcloud.demo;

import com.springcloud.demo.annotation.EnableMyResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-04 23:41
 */
@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
public class SpringCloudSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSystemApplication.class, args);
    }
}
