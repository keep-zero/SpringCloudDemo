package com.springcloud.demo.vo;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @description 自定义用户信息
 * @auther: lai.guanfu
 * @date: 2019-06-11 17:01
 */
public class TokenUserVo extends User {

    /**
     * 用户ID
     */
    @Getter
    private Integer id;

    /**
     * 用户真实姓名
     */
    @Getter
    private String name;

    public TokenUserVo(Integer id , String username, String name, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.name = name;
    }
}
