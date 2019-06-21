package com.springcloud.demo;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description 启动类
 * @EnableTransactionManagerServer 开启事务管理器
 * @auther: lai.guanfu
 * @date: 2019-06-19 15:08
 */
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagerServer
public class SpringCloudTMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTMApplication.class, args);
    }
}
