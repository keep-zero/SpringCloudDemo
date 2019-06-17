package com.springcloud.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.demo.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-05-24
 */
@Mapper
public interface ClientMapper extends BaseMapper<Client> {
    /**
     * 自定义分页方法（注：Page参数一定要放在第一位，否则会失效）
     * @param page 分页对象
     * @param client 传入对象参数
     * @return
     */
    IPage<Client> selectClientPage(Page page,@Param("client") Client client);
}
