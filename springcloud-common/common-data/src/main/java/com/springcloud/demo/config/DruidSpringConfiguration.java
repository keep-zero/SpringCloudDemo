package com.springcloud.demo.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-04-24 16:29
 */
@Aspect
@Configuration
public class DruidSpringConfiguration {
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        //指明需要监控的类
        jdkRegexpMethodPointcut.setPatterns("com.springcloud.demo.controller.*", "com.springcloud.demo.service.*");
        return jdkRegexpMethodPointcut;
    }

    @Bean
    public Advisor druidAdviceAdvisor() {
        DefaultBeanFactoryPointcutAdvisor defaultBeanFactoryPointcutAdvisor = new DefaultBeanFactoryPointcutAdvisor();
        defaultBeanFactoryPointcutAdvisor.setAdvice(druidStatInterceptor());
        defaultBeanFactoryPointcutAdvisor.setPointcut(druidStatPointcut());
        return defaultBeanFactoryPointcutAdvisor;
    }

}
