package com.springcloud.demo.config;

import com.springcloud.demo.filter.RequestTimeFilter;
import com.springcloud.demo.filter.GlobalTokenFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 网关配置
 * @auther: lai.guanfu
 * @date: 2019-04-25 17:58
 */
@Configuration
public class GatewayConfig {

    /**
     * 添加过滤器以及添加路由
     * @param
     * @return
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/document/**")
                    .filters(f -> f.filter(new RequestTimeFilter())
                            .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                    .uri("lb://springcloud-document")
                    .order(0)
                    .id("springcloud-document")
            )
            .build();
    }

    /**
     * 添加全局过滤器
     * @return
     */
    @Bean
    public GlobalTokenFilter globalTokenFilter(){
        return new GlobalTokenFilter();
    }
}
