package com.springcloud.demo.util;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @description 认证授权工具类
 * @auther: lai.guanfu
 * @date: 2019-06-06 18:24
 */
@UtilityClass
public class OAuthUtil {

    /**
     * 通过request获取到token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        //判断来源请求是否包含oauth2授权信息,
        String authToken = null;

        //授权信息来源可能是头部的Authorization值以Bearer开头
        Enumeration<String> authorization = request.getHeaders("Authorization");
        if (authorization != null&&authorization.hasMoreElements()) {
                authToken = authorization.nextElement().substring("Bearer".length()).trim();
        }

        //或者是请求参数中包含access_token参数,
        if (StrUtil.isBlank(authToken)) {
            authToken = request.getParameter("access_token");
        }

        return authToken;
    }

    /**
     * 获取当前上下文授权信息
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        throw  new AuthenticationServiceException("authentication not found");
    }

    /**
     * 获取当前上下文token的信息
     * @return
     */
    public static OAuth2AuthenticationDetails getDetails(){
        Authentication authentication = getAuthentication();
        if(authentication == null){
            return null;
        }
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        return details;
    }

    /**
     * 获取当前登入用户账号
     * @return
     */
    public static String getUsername(){
        return getAuthentication().getName();
    }

    /**
     *  获取当前登入用户的访问accessToken
     * @return
     */
    public static String getAccessToken(){
        if(getDetails() == null){
            return null;
        }
        return getDetails().getTokenValue();
    }
}
