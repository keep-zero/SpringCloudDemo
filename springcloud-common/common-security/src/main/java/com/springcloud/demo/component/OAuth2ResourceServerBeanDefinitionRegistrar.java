package com.springcloud.demo.component;

import com.springcloud.demo.config.OAuth2ResourceServerConfig;
import com.springcloud.demo.constant.SecurityConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-06 19:08
 */
@Slf4j
public class OAuth2ResourceServerBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        if (registry.isBeanNameInUse(SecurityConst.RESOURCE_SERVER_CONFIGURER)) {
//            log.warn("本地存在资源服务器配置，覆盖默认配置:" + SecurityConst.RESOURCE_SERVER_CONFIGURER);
//            return;
//        }
//        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClass(OAuth2ResourceServerConfig.class);
//        registry.registerBeanDefinition("resourceServerConfigurerAdapter",beanDefinition );
    }
}
