package com.springcloud.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description 自定义过滤器，记录请求调用时间
 * @auther: lai.guanfu
 * @date: 2019-04-25 17:09
 */
@Component
@Slf4j
public class RequestTimeFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put("startTime", System.currentTimeMillis());
        log.info("===================pre阶段====================");
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute("startTime");
                    if (startTime != null) {
                        log.info("=========post阶段==============，执行路径：{}，所耗时间：{}",exchange.getRequest().getURI().getRawPath(),(System.currentTimeMillis() - startTime) + "ms");
                    }
                }));
    }

    /**
     * 设置当前过滤器的优先级，值越大，优先级越低
     * @return
     */
    @Override
    public int getOrder() {
        return 100;
    }
}
