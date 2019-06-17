package com.springcloud.demo.constant;

/**
 * @description Security 常量
 * @auther: lai.guanfu
 * @date: 2019-06-05 21:15
 */
public class SecurityConst {

    /**
     * 项目总体统一前缀
     */
    public static final String PROJECT_PREFIX="spring_cloud_demo_";

    /**
     * 认证前缀
     */
    public static final String OAUTH_PREFIX = "oauth_";

    /**
     * 资源服务器配置标识
     */
    public static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    /**
     * 用户详情前缀
     */
    public static final String USER_DETAIL = "user_detail";


    /**
     *  token有效期（单位：秒）
     */
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 60*60;

    /**
     * 刷新token有效期（单位：秒）
     */
    public static final int REFRESH_TOKEN_VALIDITY_SECONDS = 60*60*2;

}
