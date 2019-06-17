package com.springcloud.demo.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.springcloud.demo.config.HystrixCredentialsContextConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description  Feign请求过滤器，将 Spring Security Context 中关键信息传递给 Hystrix Context
 * @auther: lai.guanfu
 * @date: 2019-06-14 19:06
 */
@Slf4j
public class OAth2FeignHystrixFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // as the comments described by HystrixRequestContext, for using HystrixRequestVariable should first initialize the context at the beginning of each request
        // so made it here...
        HystrixRequestContext.initializeContext();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if( securityContext !=null){
            Authentication auth =  securityContext.getAuthentication();
            HystrixCredentialsContextConfig.getInstance().set(auth);
            log.debug("try to register the authentication into Hystrix Context, the Authentication Object: {}",auth );
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
