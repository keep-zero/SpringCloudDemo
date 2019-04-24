package com.springcloud.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.demo.enity.Document;
import com.springcloud.demo.mapper.DocumentMapper;
import com.springcloud.demo.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description document的service实现类
 * @auther: lai.guanfu
 * @date: 2019-04-07 17:02
 */
@AllArgsConstructor
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper,Document> implements DocumentService {
    private DocumentMapper documentMapper;

    @Override
    public IPage page(IPage page, Document document) {
        AbstractWrapper abstractWrapper = new QueryWrapper(document);
        return this.documentMapper.selectPage(page, abstractWrapper);
    }
}
