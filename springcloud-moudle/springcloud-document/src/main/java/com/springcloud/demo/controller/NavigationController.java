package com.springcloud.demo.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.demo.enity.Navigation;
import com.springcloud.demo.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-04-08 11:24
 */
@RestController
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    /**
     * @description: 根据id获取栏目信息
     * @author: lai.guanfu
     * @date: 2019-04-07
     * @param id: 栏目id
     * @return:
     */
    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable Integer id){
        Navigation navigation = this.navigationService.getById(id);
        return JSONUtil.toJsonStr(navigation);
    }

    /**
     * 分页获取栏目列表
     * @param page
     * @param navigation
     * @return
     */
    @GetMapping("/page")
    public String page(Page page,Navigation navigation){
        IPage iPage = this.navigationService.page(page, navigation);
        return JSONUtil.toJsonStr(iPage);
    }

    /**
     * @description: 新增栏目
     * @author: lai.guanfu
     * @date: 2019-04-08
     * @param navigation:
     * @return:
     */
    @PostMapping("/add")
    public String add(Navigation navigation ){
        return JSONUtil.toJsonStr(Boolean.valueOf(this.navigationService.save(navigation)));
    }

    /**
     * @description: 删除栏目
     * @author: lai.guanfu
     * @date: 2019-04-08
     * @param id:
     * @return:
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return JSONUtil.toJsonStr(Boolean.valueOf(this.navigationService.removeById(id)));
    }

    /**
     * @description: 更新栏目信息
     * @author: lai.gaunfu
     * @date: 2019-04-08
     * @param navigation: 栏目对象
     * @return:
     */
    @PutMapping("/update")
    public String update(Navigation navigation){
        return JSONUtil.toJsonStr(Boolean.valueOf(this.navigationService.updateById(navigation)));
    }




}
