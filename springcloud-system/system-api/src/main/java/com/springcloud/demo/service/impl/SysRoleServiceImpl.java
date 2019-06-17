package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.demo.mapper.SysRoleMapper;
import com.springcloud.demo.service.SysRoleService;
import com.springcloud.demo.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 角色Service
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:49
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> getRoleByUserId(Integer userId) {
        return this.baseMapper.getRoleByUserId(userId);
    }
}
