package com.springcloud.demo.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.springcloud.demo.entity.SysUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description 安全服务工具类
 * @auther: lai.guanfu
 * @date: 2019-06-06 21:15
 */
@UtilityClass
public class SecurityUtil {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     *  获取用户基本认证信息
     * @param authentication 认证对象
     * @return
     */
    public SysUser getUser(Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof User){
            return JSONUtil.toBean(JSONUtil.toJsonStr(principal), SysUser.class);
        }
        return null;
    }

    /**
     *  获取认证用户姓名
     * @return
     */
    public String getUserName(){
        SysUser user = getUser();
        if(ObjectUtil.isNotNull(user)){
            return user.getUsername();
        }
        return null;
    }



    /**
     * 获取用户
     * @return
     */
    public SysUser getUser(){
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Integer> roleIds = new ArrayList<>();
        authorities.stream()
                .forEach(granted -> {
                    String id = granted.getAuthority();
                    roleIds.add(Integer.parseInt(id));
                });
        return roleIds;
    }




}
