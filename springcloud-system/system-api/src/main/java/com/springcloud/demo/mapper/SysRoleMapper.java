package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 角色mapper
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:47
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户id获取用户角色列表
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(Integer userId);
}
