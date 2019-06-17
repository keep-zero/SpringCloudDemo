package com.springcloud.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.springcloud.demo.enums.RespEnum;
import com.springcloud.demo.helper.UserHelper;
import com.springcloud.demo.util.OAuthUtil;
import com.springcloud.demo.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 用户注销前端控制器
 * @auther: lai.guanfu
 * @date: 2019-06-05 12:01
 */
@RestController
@RequestMapping("/oauth")
@Slf4j
public class LogoutController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserHelper userHelper;

    @DeleteMapping(value = "/logout")
    public R revokeToken(HttpServletRequest request){

        String access_token = OAuthUtil.getToken(request);

        log.info("用户注销，token={}",access_token);

        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);

        if (accessToken == null) {
            return R.failed(RespEnum.NO_LOGIN);
        }

        User user = (User)tokenStore.readAuthentication(accessToken).getPrincipal();
        Object username = accessToken.getAdditionalInformation().get("username");
        if(ObjectUtil.isNotNull(username)){
            userHelper.removeUser(username.toString());
        }

        if (accessToken.getRefreshToken() != null) {
            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }
        tokenStore.removeAccessToken(accessToken);

        return R.suc("注销成功");
    }
}
