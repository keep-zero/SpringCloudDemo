package com.springcloud.demo.vo;

import com.springcloud.demo.entity.SysMenu;
import com.springcloud.demo.entity.SysRole;
import com.springcloud.demo.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 用户信息封装类
 * @auther: lai.guanfu
 * @date: 2019-06-12 13:45
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysUserVo{
    /**
     * 用户基本信息
     */
    private SysUser user;

    /**
     * 用户角色列表
     */
    private List<SysRole> roles;

    /**
     * 用户权限菜单列表
     */
    private List<SysMenu> menus;
}
