package com.springcloud.demo.service;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.enity.Document;


/**
 * @description: document的service接口
 * @auther: lai.guanfu
 * @date: 2019-04-07 17:01
 */
public interface DocumentService extends IService<Document> {

    /**
     * 查询分页
     * @param page
     * @param document
     * @return
     */
    IPage page(IPage page, Document document);

}
