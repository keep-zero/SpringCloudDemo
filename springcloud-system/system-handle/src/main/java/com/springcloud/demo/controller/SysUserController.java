package com.springcloud.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.springcloud.demo.entity.SysUser;
import com.springcloud.demo.helper.RedisHelper;
import com.springcloud.demo.helper.UserHelper;
import com.springcloud.demo.service.SysUserService;
import com.springcloud.demo.util.EncodeUtil;
import com.springcloud.demo.util.R;
import com.springcloud.demo.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-04 23:00
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class SysUserController {
    private SysUserService sysUserService;
    private UserHelper userHelper;


    /**
     * 通过用户id获取用户信息
     * @param userId 用户id
     * @return
     */
    @GetMapping("/{userId}")
    public R getUser(@PathVariable  String userId){
        return R.suc(this.sysUserService.getById(userId));
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @GetMapping("/info")
    public R getUserInfo(){
        SysUser user = this.userHelper.getUserInfo();
        return R.suc(user);
    }

    /**
     * 用户注册
     * @param sysUser 用户信息
     * @return
     */
    @PostMapping
    public R addUserInfo(@RequestBody SysUser sysUser){

        //用户名判重
        if(ObjectUtil.isNotNull(this.sysUserService.findByUsername(sysUser.getUsername()))){
            return R.failed("该账户名已存在，请核验后注册");
        }

        /**
         * 密码加密
         */
        String password = sysUser.getPassword().trim();
        String encodedPassword = EncodeUtil.encode(password);
        sysUser.setPassword(encodedPassword);

        return R.suc(this.sysUserService.save(sysUser));
    }
}
