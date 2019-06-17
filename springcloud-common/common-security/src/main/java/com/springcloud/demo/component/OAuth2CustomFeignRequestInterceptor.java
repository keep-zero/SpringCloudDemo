package com.springcloud.demo.component;

import com.springcloud.demo.config.HystrixCredentialsContextConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @description 实现RequestInterceptor接口，作为Feign请求拦截，为请求添加认证所需的access token及user
 * @auther: lai.guanfu
 * @date: 2019-06-14 19:03
 */
@Slf4j
public class OAuth2CustomFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication auth = HystrixCredentialsContextConfig.getInstance().get();
        if (auth != null) {
            log.debug("try to forward the authentication by Hystrix, the Authentication Object: {}", auth);
            // 记得，因为 Feign Interceptor 是通过自有的 ThreadPool 中的线程执行的，与当前的 Request 线程不是同一个线程，所以这里不能使用 debug 模式进行调试；
            requestTemplate.header("Authorization", "bearer " + ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue());
        } else {
            log.debug("attention, there is no Authentication Object needs to forward");
        }
    }
}
