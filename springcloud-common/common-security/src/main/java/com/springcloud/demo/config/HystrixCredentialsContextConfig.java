package com.springcloud.demo.config;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import com.springcloud.demo.component.OAuth2CustomFeignRequestInterceptor;
import com.springcloud.demo.filter.OAth2FeignHystrixFilter;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

/**
 * @description https://mp.weixin.qq.com/s/b1PZl8OVj3m7Q5IaJR_dpQ
 * @auther: lai.guanfu
 * @date: 2019-06-14 18:41
 */
    @Configuration
    @Slf4j
    public class HystrixCredentialsContextConfig {

        private static final HystrixRequestVariableDefault<Authentication> authentication = new HystrixRequestVariableDefault<>();

        public static HystrixRequestVariableDefault<Authentication> getInstance(){
            return authentication;
        }

        /**
         * 下面这段代码是关键，实现 @See feign.RequestInterceptor，
         * 1. 添加认证所需的 oauth token；
         * 2. 添加认证所需的 user;
         *
         * 目前仅实现了 oauth toke，将来看情况是否实现 user;
         *
         * 特别要注意一点，因为 HystrixRequestContext 和 RequestContext 不在同一个线程中，所以，不能直接在 RequestInterceptor 的实现方法中调用 RequestContext 中的资源，因为 HystrixRequestContext 是在自己
         * 的 ThreadPool 中执行的；所以，这里搞得比较的麻烦... 不能在 {@link RequestInterceptor#apply(RequestTemplate)} 中直接使用 RequestContext / SecurityContextHolder，否则取到的资源全部是 null；
         *
         * @return
         */
        @Bean
        public RequestInterceptor requestInterceptor() {
            return new OAuth2CustomFeignRequestInterceptor();
        }

        @Bean
        public FilterRegistrationBean  hystrixFilter() {
            FilterRegistrationBean r = new FilterRegistrationBean();
            r.setFilter(new OAth2FeignHystrixFilter());
            r.addUrlPatterns("/*");
            return r;
        }
    }
