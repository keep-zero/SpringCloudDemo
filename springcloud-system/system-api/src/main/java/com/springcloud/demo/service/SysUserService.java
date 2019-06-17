package com.springcloud.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.entity.SysUser;

/**
 * @description 用户Service
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:48
 */
public interface SysUserService extends IService<SysUser> {

    /**
     *  根据用户名查询用户
     * @param username 用户名-
     * @return
     */
    SysUser findByUsername(String username);
}
