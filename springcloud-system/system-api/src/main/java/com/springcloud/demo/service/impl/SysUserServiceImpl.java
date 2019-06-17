package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.demo.mapper.SysUserMapper;
import com.springcloud.demo.service.SysUserService;
import com.springcloud.demo.entity.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description 用户Service
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:49
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findByUsername(String username) {
       return this.baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
    }
}
