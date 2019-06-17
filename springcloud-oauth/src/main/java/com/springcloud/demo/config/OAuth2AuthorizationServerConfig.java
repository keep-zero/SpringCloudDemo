package com.springcloud.demo.config;

import com.springcloud.demo.component.CustomTokenEnhancer;
import com.springcloud.demo.constant.SecurityConst;
import com.springcloud.demo.translator.MssWebResponseExceptionTranslator;
import com.springcloud.demo.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @description 授权服务器配置
 * @EnableAuthorizationServer 启用授权服务
 * @auther: lai.guanfu
 * @date: 2019-06-04 15:19
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager; // 认证管理器

    @Autowired
    private DataSource dataSource; //数据库连接

    @Autowired
    private UserDetailServiceImpl userDetailsService; //用户信息处理器

    @Autowired
    private RedisConnectionFactory redisConnectionFactory; // redis连接工厂

    /**
     * 声明TokenStore实现 说明令牌存储对象
     * 有InMemoryTokenStore(内存)，JdbcTokenStore（数据库），RedisTokenStore（redis缓存），JwtTokenStore（jwt）四个实现
     * redis因其过期时间特性，尤为适合用于存储令牌
     * @return
     */
    @Bean(name = "tokenStore")
    TokenStore tokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);

        /**
         * 设定token存储在redis中数据的前缀
         */
//        redisTokenStore.setPrefix(SecurityConst.PROJECT_PREFIX+SecurityConst.OAUTH_PREFIX);

        return redisTokenStore;
    }

    /**
     * 设置客户端细节信息处理器
     * 配置客户端详情服务（ClientDetailsService）
     * 客户端详情信息在这里进行初始化
     * 通过数据库来存储调取详情信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    /**
     *  配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     * @param endpoints 终端对象
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){

        endpoints.tokenStore(tokenStore()) //设置令牌存储对象
                .userDetailsService(userDetailsService) //设置用户信息处理器
                .authenticationManager(authenticationManager) //设置认证处理器
                .tokenEnhancer(new CustomTokenEnhancer())   //token增强
                .reuseRefreshTokens(true) //该字段设置设置refresh token是否重复使用,即通过刷新token，是否保留原有refresh而不会重新获取,true:reuse;false:no reuse.
                .exceptionTranslator(new MssWebResponseExceptionTranslator());//认证异常转换


        // 为解决获取token并发问题
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore()); //设置令牌存储对象
        tokenServices.setTokenEnhancer(new CustomTokenEnhancer()); //设置令牌增强
        tokenServices.setAuthenticationManager(authenticationManager); // 设置令牌处理器
        tokenServices.setSupportRefreshToken(true); //支持刷新token
        tokenServices.setClientDetailsService(clientDetails()); //设置终端管理
        tokenServices.setAccessTokenValiditySeconds(SecurityConst.ACCESS_TOKEN_VALIDITY_SECONDS); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(SecurityConst.REFRESH_TOKEN_VALIDITY_SECONDS);//默认30天，这里修改

        endpoints.tokenServices(tokenServices);
    }

    /**
     * 配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param security security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security .checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients(); //允许表单认证提交
    }

    /**
     *  声明 ClientDetails实现
     * 有JdbcClientDetailsService【客户端信息存在于数据库】及InMemoryClientDetailsService[客户端信息存在于内存]两个实现
     * 此处可自定义实现redis,暂定使用jdbc方式
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        return jdbcClientDetailsService;
    }



    /**
     * 注册Token处理器，作为oauth2中操作token(crud)的默认实现
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则会有3个同类型的Bean,无法注入报错，</p>
     * @return
     */
//    @Primary
//    @Bean
//    public DefaultTokenServices defaultTokenServices(){
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
////        tokenServices.setTokenStore(tokenStore()); //设置令牌存储对象
////        tokenServices.setTokenEnhancer(tokenEnhancer()); //设置令牌增强
////        tokenServices.setAuthenticationManager(authenticationManager); // 设置令牌处理器
////        tokenServices.setSupportRefreshToken(true); //支持刷新token
////        tokenServices.setClientDetailsService(clientDetails()); //设置终端管理
////        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
////        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
//        return tokenServices;
//    }


}
