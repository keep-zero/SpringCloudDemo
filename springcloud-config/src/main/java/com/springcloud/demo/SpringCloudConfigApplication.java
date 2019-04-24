package com.springcloud.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-04-18 16:22
 */
@SpringCloudApplication
@EnableConfigServer
public class SpringCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigApplication.class);
    }

}
