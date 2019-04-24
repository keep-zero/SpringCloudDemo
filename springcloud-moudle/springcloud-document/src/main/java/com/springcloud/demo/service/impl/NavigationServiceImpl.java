package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.demo.enity.Navigation;
import com.springcloud.demo.mapper.NavigationMapper;
import com.springcloud.demo.service.NavigationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-04-08 10:49
 */
@Service
@AllArgsConstructor
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {
    private NavigationMapper navigationMapper;

    @Override
    public IPage page(Page page, Navigation navigation) {
        QueryWrapper<Navigation> navigationQueryWrapper = new QueryWrapper<>(navigation);
        return this.navigationMapper.selectPage(page, navigationQueryWrapper);
    }
}
