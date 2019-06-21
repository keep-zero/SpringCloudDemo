package com.springcloud.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.springcloud.demo.entity.ClientTotal;
import com.springcloud.demo.mapper.ClientTotalMapper;
import com.springcloud.demo.service.ClientTotalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-19
 */
@Service
public class ClientTotalServiceImpl extends ServiceImpl<ClientTotalMapper, ClientTotal> implements ClientTotalService {


    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    @Override
    public void saveClient(String clientPath) throws  InterruptedException {
        ClientTotal clientTotal = this.baseMapper.selectOne(new QueryWrapper<ClientTotal>().eq("client_path", clientPath));
        Thread.sleep(5000); //线程休眠5秒，造成访问超时
        //如果不为空，则更新客户端总数
        if(ObjectUtil.isNotNull(clientTotal)){
            clientTotal.setClientTotal(clientTotal.getClientTotal()+1);
            this.baseMapper.updateById(clientTotal);
        }else{//否则，新增客户端路径
            ClientTotal newClientTotal = new ClientTotal();
            newClientTotal.setClientPath(clientPath);
            newClientTotal.setClientTotal(1);
            this.baseMapper.insert(newClientTotal);
        }
    }
}
