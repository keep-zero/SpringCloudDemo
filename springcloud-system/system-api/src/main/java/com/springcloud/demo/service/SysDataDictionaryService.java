package com.springcloud.demo.service;

import com.springcloud.demo.entity.SysDataDictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-08-06
 */
public interface SysDataDictionaryService extends IService<SysDataDictionary> {

    /**
     * 扫描数据表f返回数据表结构
     * @param database 数据库名
     * @return
     */
    List<SysDataDictionary> scanDataTable(String database);

    /**
     * 扫描数据表f返回数据表结构
     * @param database 数据库名
     * @return
     */
    List<SysDataDictionary> scanDataTable(String database,String table);

}
