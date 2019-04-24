package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.enity.DocNavMapping;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 文档和栏目的映射类mapper
 * @auther: lai.guanfu
 * @date: 2019-04-08 11:18
 */
@Mapper
public interface DocNavMappingMapper extends BaseMapper<DocNavMapping> {
}
