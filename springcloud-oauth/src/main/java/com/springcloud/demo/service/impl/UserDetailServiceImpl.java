package com.springcloud.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.springcloud.demo.entity.SysMenu;
import com.springcloud.demo.entity.SysRole;
import com.springcloud.demo.entity.SysUser;
import com.springcloud.demo.helper.RedisHelper;
import com.springcloud.demo.helper.UserHelper;
import com.springcloud.demo.service.SysPermissionService;
import com.springcloud.demo.service.SysRoleService;
import com.springcloud.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description 用户信息服务，实现 Spring Security的UserDetailsService接口方法，用于身份认证
 * @auther: lai.guanfu
 * @date: 2019-06-04 15:39
 */
@Slf4j
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    //用户信息service
    @Autowired
    private SysUserService userService;
    //角色信息service
    @Autowired
    private SysRoleService roleService;
    //用户权限信息service
    @Autowired
    private SysPermissionService permissionService;

    //专用于登录用户的redis工具类，封装了用户基本信息操作
    @Autowired
    private UserHelper userHelper;

    //redis工具类
    @Autowired
    private RedisHelper redisHelper;

    /**
     * 根据用户名查找账户信息并返回用户信息实体
     * @param username 用户名
     * @return 用于身份认证的 UserDetails 用户信息实体
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        boolean enabled = true; // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定

        //判断缓存中是否存在以该用户名为key的用户信息，如果有，则直接封装返回
        if(StrUtil.isNotBlank(redisHelper.get(username))){
            SysUser sysUser = JSONUtil.toBean(redisHelper.get(username), SysUser.class);
            return new User(sysUser.getUsername(),sysUser.getPassword(),
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        }
        //从数据库中查询获取该账户信息
        SysUser  sysUser = this.userService.findByUsername(username);

        if (ObjectUtil.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户:" + username + ",不存在!");
        }

        //获取用户角色列表
        List<SysRole> roles = this.roleService.getRoleByUserId(sysUser.getId());
        if (!CollUtil.isEmpty(roles)){
            for (SysRole role:roles){
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getValue());
                grantedAuthorities.add(grantedAuthority);

                //获取用户权限列表
                List<SysMenu> permissions  = this.permissionService.getPermissionsByRoleId(role.getId());
                if (!CollUtil.isEmpty(permissions)){
                    for (SysMenu menu:permissions) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(menu.getCode());
                        grantedAuthorities.add(authority);
                    }
                }
            }
        }

        //将当前用户存入redis缓存
        userHelper.addUser(sysUser);

        //封装返回
        return new User(sysUser.getUsername(),sysUser.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}