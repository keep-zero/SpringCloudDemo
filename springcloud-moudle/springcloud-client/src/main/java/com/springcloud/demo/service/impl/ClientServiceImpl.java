package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.demo.entity.Client;
import com.springcloud.demo.mapper.ClientMapper;
import com.springcloud.demo.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-05-24
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Override
    public IPage<Client> getClientPage(Page page, Client client) {
        return this.baseMapper.selectClientPage(page,client);
    }
}
