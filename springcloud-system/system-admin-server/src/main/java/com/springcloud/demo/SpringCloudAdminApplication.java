package com.springcloud.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * @description 系统监控服务
 * @auther: lai.guanfu
 * @date: 2019-06-17 10:06
 */
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class SpringCloudAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAdminApplication.class, args);
    }
}
