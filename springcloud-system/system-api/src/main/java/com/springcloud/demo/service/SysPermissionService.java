package com.springcloud.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.entity.SysMenu;

import java.util.List;

/**
 * @description 权限（菜单）service
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:48
 */
public interface SysPermissionService extends IService<SysMenu> {
    /**
     * 根据角色id获取菜单列表
     * @param roleId
     * @return
     */
    List<SysMenu> getPermissionsByRoleId(Integer roleId);
}
