package com.springcloud.demo;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @description 启动类
 * @EnableDistributedTransaction 开启分布式事务
 * @EnableFeignClients 开启OpenFeign
 * @EnableResourceServer 开启安全资源服务
 * @auther: lai.guanfu
 * @date: 2019-05-15 0:28
 */
@EnableDistributedTransaction
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
public class SpringCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientApplication.class, args);
    }

}
