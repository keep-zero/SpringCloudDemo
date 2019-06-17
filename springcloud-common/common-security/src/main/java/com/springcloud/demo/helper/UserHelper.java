package com.springcloud.demo.helper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.springcloud.demo.constant.SecurityConst;
import com.springcloud.demo.entity.SysUser;
import com.springcloud.demo.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description 用户信息获取类
 * @auther: lai.guanfu
 * @date: 2019-06-12 9:52
 */
@Slf4j
@Component
public class UserHelper {

    @Autowired
    private RedisHelper redisHelper;

    /**
     * 获取当前登录用户详情
     */
    public SysUser getUserInfo(){
        String userName = SecurityUtil.getUserName();

        if(log.isDebugEnabled()){
            log.debug("获取当前登录用户信息，用户名：{}",userName);
        }
        if(StrUtil.isNotBlank(userName)){
            Object userObj= redisHelper.hget(SecurityConst.USER_DETAIL, userName);
            if(ObjectUtil.isNotNull(userObj)){
                return JSONUtil.toBean(JSONUtil.toJsonStr(userObj), SysUser.class);
            }
        }
        return null;
    }

    /**
     * 获取当前登录用户名
     */
    public String getUserName(){
        String userName = SecurityUtil.getUserName();

        if(log.isDebugEnabled()){
            log.debug("获取当前登录用户名：{}",userName);
        }

        return userName;
    }

    /**
     * 移除用户
     * @param userName
     * @return
     */
    public boolean removeUser(String userName){
        if(log.isDebugEnabled()){
            log.debug("移除用户信息：{}",userName);
        }
        this.redisHelper.hdel(SecurityConst.USER_DETAIL,userName);
        return true;
    }

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    public boolean addUser(SysUser sysUser){
        if(log.isDebugEnabled()){
            log.debug("新增用户信息：{}",sysUser);
        }
        this.redisHelper.hset(SecurityConst.USER_DETAIL, sysUser.getUsername(),JSONUtil.toJsonStr(sysUser),SecurityConst.ACCESS_TOKEN_VALIDITY_SECONDS);
        return true;
    }
}
