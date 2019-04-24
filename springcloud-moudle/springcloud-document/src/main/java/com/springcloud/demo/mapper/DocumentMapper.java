package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.demo.enity.Document;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 文档的mapper
 * @author: lai.guanfu
 * @date: 2019-04-07 16:07:53
 */
@Mapper
public interface DocumentMapper extends BaseMapper<Document> {
}
