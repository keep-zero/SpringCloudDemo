package com.springcloud.demo.annotation;

import com.springcloud.demo.component.OAuth2ResourceServerBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;

import java.lang.annotation.*;

/**
 * @description 自定义开启资源服务器配置
 * @auther: lai.guanfu
 * @date: 2019-06-06 19:02
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(OAuth2ResourceServerBeanDefinitionRegistrar.class)
public @interface EnableMyResourceServer {
}
