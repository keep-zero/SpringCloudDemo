package com.springcloud.demo.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description spring工具类
 * @auther: lai.guanfu
 * @date: 2019-06-26 14:12
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    protected static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtil.getAppContext()获取applicationContext对象,applicationContext=" + SpringUtil.applicationContext + "========");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean的注册名称获取bean
     * @param beanName bean注册名
     * @return
     */
    public static Object getBeanByName(String beanName){
        if(getApplicationContext().containsBeanDefinition(beanName)){
            return getApplicationContext().getBean(beanName);
        }
        return null;
    }

    /**
     * 通过class类型获取bean
     * @param clazz class类型
     * @param <T> 泛型类型
     * @return
     */
    public static <T> T getBeanByClass(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过class类型以及baen注册名获取指定的bean
     * @param beanName bean注册名
     * @param clazz class类型
     * @param <T> 泛型类型
     * @return
     */
    public static <T> T getBean(String beanName,Class<T> clazz){
        return getApplicationContext().getBean(beanName, clazz);
    }

    /**
     * 判断bean是否存在于容器
     * @param beanName bean注册名
     * @return
     */
    public static boolean containBean(String beanName){
        if(ObjectUtil.isNull(getBeanByName(beanName))){
            return false;
        }
        return true;
    }
}
