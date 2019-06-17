package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 用户mapper
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:47
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
