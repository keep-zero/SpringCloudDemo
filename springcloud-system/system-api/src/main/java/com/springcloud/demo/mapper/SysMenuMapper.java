package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 菜单Mapper
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:47
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色id获取权限列表
     * @param roleId
     * @return
     */
    List<SysMenu> getPermissionsByRoleId(Integer roleId);
}
