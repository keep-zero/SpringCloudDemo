package com.springcloud.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.entity.Client;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-05-24
 */
public interface ClientService extends IService<Client> {

    /**
     * 自定义分页查询
     * @param page 分页对象
     * @param client 传入对象参数
     * @return
     */
    IPage<Client> getClientPage(Page page, Client client);

    /**
     * 新增client,用于测试分布式事务
     * @param client client对象
     * @return
     */
    void addClient(Client client);
}
