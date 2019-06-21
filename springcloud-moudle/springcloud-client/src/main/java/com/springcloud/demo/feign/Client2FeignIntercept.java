package com.springcloud.demo.feign;

import com.springcloud.demo.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description client2的feign接口
 * @auther: lai.guanfu
 * @date: 2019-06-14 12:55
 */
@FeignClient(value = "springcloud-client2",name = "springcloud-client2") //指向注册名为springcloud-client2的服务
public interface Client2FeignIntercept {

    /**
     * 内部接口，增加客户端信息
     * @return
     */
    @PostMapping("/clientTotal") //指向springcloud-client2中接口路径为/clientTotal的POST接口
    R addClient(@RequestParam String clientPath);
}
