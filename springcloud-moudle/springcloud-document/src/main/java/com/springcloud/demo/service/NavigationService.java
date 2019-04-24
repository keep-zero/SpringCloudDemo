package com.springcloud.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.enity.Navigation;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-04-08 10:48
 */
public interface NavigationService extends IService<Navigation> {

    /**
     * 查询分页
     * @param page
     * @param navigation
     * @return
     */
    IPage page(Page page, Navigation navigation);

}
