package com.springcloud.demo.service.impl;

import com.springcloud.demo.entity.SysDataDictionary;
import com.springcloud.demo.mapper.SysDataDictionaryMapper;
import com.springcloud.demo.service.SysDataDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-08-06
 */
@Service
public class SysDataDictionaryServiceImpl extends ServiceImpl<SysDataDictionaryMapper, SysDataDictionary> implements SysDataDictionaryService {

    @Override
    public List<SysDataDictionary> scanDataTable(String database) {
        return this.baseMapper.scanDataTable(database);
    }
}
