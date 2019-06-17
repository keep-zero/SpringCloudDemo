package com.springcloud.demo.config;

import cn.hutool.core.util.StrUtil;
import com.springcloud.demo.util.OAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 资源服务配置
 * @EnableResourceServer 启用资源服务
 * @auther: lai.guanfu
 * @date: 2019-06-04 15:22
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory; // redis连接工厂

    /**
     *  与http安全配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/swagger-ui.html", "/swagger-resources/**",
                    "/v2/api-docs/**", "/validatorUrl","/valid","/actuator/**")
                    .permitAll()            //匹配不需要资源认证路径
                     .anyRequest().authenticated()
                    .and()
                    .httpBasic();
    }

    /**
     *  资源安全配置
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);

        //设置访问错误时的处理器，该处理器内部通过自动访问刷新token的方式实现隐式token续签
//        resources.authenticationEntryPoint(new CustomAuthorizationEntryPoint());
        //此处如果不进行设置，资源服务器的鉴权认证将会默认从InMemoryTokenStore取数据验证
        resources.tokenStore(new RedisTokenStore(redisConnectionFactory));
    }

    /**
     * 定义OAuth2请求匹配器
     */
    private static class OAuth2RequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            //判断来源请求是否包含oauth2授权信息,这里授权信息来源可能是头部的Authorization值以Bearer开头,或者是请求参数中包含access_token参数,满足其中一个则匹配成功
            String token = OAuthUtil.getToken(request);
            return StrUtil.isBlank(token);
        }
    }

}
