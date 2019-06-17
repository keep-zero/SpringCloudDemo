package com.springcloud.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.entity.SysRole;

import java.util.List;

/**
 * @description 角色 Service
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:48
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户id获取用户角色列表
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(Integer userId);
}
