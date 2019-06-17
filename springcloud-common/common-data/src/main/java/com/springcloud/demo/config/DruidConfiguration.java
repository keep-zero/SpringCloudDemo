package com.springcloud.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description Druid数据源的配置
 * @auther: lai.guanfu
 * @date: 2019-04-24 11:43
 */
@Configuration
public class DruidConfiguration {

    /**
     * 注册Servlet信息， 配置监控视图(由于yml文件无法直接设置loginUsername和loginPassword属性，故使用java配置的方式)
     */
    @Bean
    public ServletRegistrationBean druidServlet() {

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        //stat视图设置
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap();
        initParameters.put("loginUsername", "admin"); // 用户名
        initParameters.put("loginPassword", "admin"); // 密码
        initParameters.put("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能
        initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow),如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean(name = "wallFilter")
    public WallFilter wallFilter() {
        WallConfig wallConfig = new WallConfig();
        wallConfig.setDeleteWhereNoneCheck(true);

        wallConfig.setMultiStatementAllow(true); //允许sql批量操作
        wallConfig.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
        WallFilter wallfilter = new WallFilter();
        wallfilter.setConfig(wallConfig);

        return wallfilter;
    }

    /**
     * 注册Filter信息, 监控拦截器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        WebStatFilter webStatFilter = new WebStatFilter();
        webStatFilter.setProfileEnable(true); //开启
        webStatFilter.setSessionStatEnable(true);
        filterRegistrationBean.setFilter(webStatFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        initParameters.put("sessionStatMaxCount", "1000");
        initParameters.put("principalCookieName", "USER_COOKIE");
        initParameters.put("principalSessionName", "USER_SESSION");
        initParameters.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
