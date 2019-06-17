package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.demo.mapper.SysMenuMapper;
import com.springcloud.demo.service.SysPermissionService;
import com.springcloud.demo.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 权限（菜单）Service
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:49
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysPermissionService {
    @Override
    public List<SysMenu> getPermissionsByRoleId(Integer roleId) {
        return this.baseMapper.getPermissionsByRoleId(roleId);
    }
}
