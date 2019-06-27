package com.springcloud.demo.service;

import com.springcloud.demo.entity.SysSchedule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-24
 */
public interface SysScheduleService extends IService<SysSchedule> {

    /**
     * 通过bean名称及方法名查询记录
     * @param scheBeanName bean名称
     * @param scheMethod 方法名
     * @return
     */
    SysSchedule findByBeanNameAndMethod(String scheBeanName, String scheMethod);
}
