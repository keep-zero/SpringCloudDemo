package com.springcloud.demo.feign;

import com.springcloud.demo.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-14 12:55
 */
@FeignClient(value = "springcloud-client",name = "springcloud-client") //指向注册名为springcloud-client的服务
public interface TestFeignIntercept {

    /**
     * 内部接口，获取信息
     * @return
     */
    @GetMapping("/test/feign") //指向springcloud-client中接口路径为/feign/test的接口
    R getFeignMassage();
}
