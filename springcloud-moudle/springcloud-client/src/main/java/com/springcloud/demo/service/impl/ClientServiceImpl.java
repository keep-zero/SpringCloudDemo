package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.springcloud.demo.entity.Client;
import com.springcloud.demo.feign.Client2FeignIntercept;
import com.springcloud.demo.mapper.ClientMapper;
import com.springcloud.demo.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-05-24
 */
@AllArgsConstructor
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
    //内部调用接口的feignclient
    private Client2FeignIntercept client2FeignIntercept;


    @Override
    public IPage<Client> getClientPage(Page page, Client client) {
        return this.baseMapper.selectClientPage(page,client);
    }

    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    @Override
    public void addClient(Client client) {
        this.baseMapper.insert(client);
        this.client2FeignIntercept.addClient(client.getClientPath());
    }
}
