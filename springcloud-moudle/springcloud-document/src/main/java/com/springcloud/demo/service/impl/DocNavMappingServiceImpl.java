package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.demo.enity.DocNavMapping;
import com.springcloud.demo.mapper.DocNavMappingMapper;
import com.springcloud.demo.service.DocNavMappingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description: 文档与栏目映射类的service
 * @auther: lai.guanfu
 * @date: 2019-04-08 11:23
 */
@Service
@AllArgsConstructor
public class DocNavMappingServiceImpl extends ServiceImpl<DocNavMappingMapper, DocNavMapping> implements DocNavMappingService {
    private DocNavMappingMapper docNavMappingMapper;
}
