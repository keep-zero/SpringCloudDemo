package com.springcloud.demo.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.springcloud.demo.constant.SecurityConst;
import com.springcloud.demo.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @description 全局请求过滤器，核验身份授权
 * @auther: lai.guanfu
 * @date: 2019-04-25 18:07
 */
@Slf4j
@Component
public class GlobalTokenFilter implements GlobalFilter, Ordered {

    // url匹配器
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return -500;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //取token
        String accessToken = extractToken(exchange.getRequest());
        //取路径
        String path = exchange.getRequest().getPath().value();

        //路径放行
        if(pathMatcher.match("/oauth/**",path)){
            return chain.filter(exchange);
        }

        //拦截非/oauth/的路径
        if(!pathMatcher.match("/oauth/**",path)){
            //如果token为空，直接返回未认证
            if (accessToken == null) {
                return unauthorizedResult(exchange,"尚未登录");
            }else{
                try {
                    //从缓存取出token核验，如果存在相应token，则放行
//                    String params =  redisTemplate.opsForValue().get(SecurityConst.PROJECT_PREFIX+SecurityConst.OAUTH_PREFIX+"auth:" +accessToken) ;
                    String params =  redisTemplate.opsForValue().get("auth:" +accessToken) ;
                    //如果不存在该token值，则直接返回未认证
                    if(StrUtil.isBlank(params)){
                        throw new Exception("无鉴权信息，请先登陆");
                    }
                } catch (Exception e) {
                    return unauthorizedResult(exchange, e.getMessage());
                }
            }
        }

        return chain.filter(exchange);
    }

    /**
     * 从request请求中提取token
     * @param request
     * @return
     */
    protected String extractToken(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get("Authorization");
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0).substring("Bearer".length()).trim();
        }

        if (StrUtil.isBlank(authToken)) {
            strings = request.getQueryParams().get("access_token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }

        return authToken;
    }

    public Mono<Void> unauthorizedResult(ServerWebExchange exchange,String message){
        ServerHttpResponse response = exchange.getResponse();
        R rsp = new R();
        rsp.setCode(HttpStatus.UNAUTHORIZED.value());
        rsp.setMsg(message);
        rsp.setData(HttpStatus.UNAUTHORIZED.getReasonPhrase());

        byte[] bits = JSONUtil.toJsonStr(rsp).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        /**
         * 设置响应状态为401
         */
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}
