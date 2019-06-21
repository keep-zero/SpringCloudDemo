package com.springcloud.demo.service;

import com.springcloud.demo.entity.ClientTotal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.demo.util.R;

import java.sql.SQLDataException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-19
 */
public interface ClientTotalService extends IService<ClientTotal> {

    void saveClient(String clientPath) throws SQLDataException, InterruptedException;
}
