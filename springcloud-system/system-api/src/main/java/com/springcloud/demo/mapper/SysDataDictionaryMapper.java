package com.springcloud.demo.mapper;

import com.springcloud.demo.entity.SysDataDictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-08-06
 */
@Mapper
public interface SysDataDictionaryMapper extends BaseMapper<SysDataDictionary> {

    /**
     * 扫描所有数据库表并将数据库表结构存入数据字典
     */
    List<SysDataDictionary> scanDataTable(String database);

}
