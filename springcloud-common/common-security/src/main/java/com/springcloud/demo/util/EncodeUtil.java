package com.springcloud.demo.util;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-12 16:33
 */
@UtilityClass
public class EncodeUtil {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 加密密码
     * @param password
     * @return
     */
    public String encode(String password){
        if(StrUtil.isNotBlank(password)){
            return passwordEncoder.encode(password);
        }
        return null;
    }

    /**
     *  密码比对
     * @param password 未加密密码（待比对密码）
     * @param encodedPassword 已加密密码（目标比对密码）
     * @return
     */
    public boolean matches(String password,String encodedPassword){
        /**
         * 判空后比对
         */
        if(StrUtil.isNotBlank(password)&&StrUtil.isNotBlank(encodedPassword)){
            return passwordEncoder.matches(password,encodedPassword);
        }

        return false;
    }



}
