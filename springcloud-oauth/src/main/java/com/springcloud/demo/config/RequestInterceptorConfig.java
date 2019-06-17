package com.springcloud.demo.config;

import com.springcloud.demo.util.OAuthUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import static org.springframework.boot.actuate.trace.http.Include.AUTHORIZATION_HEADER;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-14 16:21
 */@Slf4j
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {

    public static final String BEARER = "Bearer";

    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {

        Authentication authentication = OAuthUtil.getAuthentication();
        if(authentication!=null && authentication.getDetails() instanceof OAuth2AuthenticationDetails){
            OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            log.debug("RequestInterceptorConfig accessToken :" +authenticationDetails.getTokenValue());
            template.header(AUTHORIZATION,
                    String.format("%s %s",
                            BEARER,
                            authenticationDetails.getTokenValue()));
        }

    }
}
