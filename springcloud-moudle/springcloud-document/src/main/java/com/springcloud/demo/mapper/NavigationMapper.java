package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.enity.Navigation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 文档栏目的mapper
 * @auther: lai.guanfu
 * @date: 2019-04-08 10:48
 */
@Mapper
public interface NavigationMapper extends BaseMapper<Navigation> {
}
