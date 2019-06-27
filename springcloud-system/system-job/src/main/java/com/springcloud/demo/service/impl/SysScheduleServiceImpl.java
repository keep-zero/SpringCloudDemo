package com.springcloud.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.demo.entity.SysSchedule;
import com.springcloud.demo.mapper.SysScheduleMapper;
import com.springcloud.demo.service.SysScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-24
 */
@Service
public class SysScheduleServiceImpl extends ServiceImpl<SysScheduleMapper, SysSchedule> implements SysScheduleService {

    @Override
    public SysSchedule findByBeanNameAndMethod(String scheBeanName, String scheMethod) {
        return this.baseMapper.selectOne(new QueryWrapper<SysSchedule>().eq("sche_bean_name", scheBeanName).eq("sche_method", scheBeanName));
    }
}
