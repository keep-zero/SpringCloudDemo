package com.springcloud.demo.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.springcloud.demo.constant.CommonConst;
import com.springcloud.demo.entity.SysSchedule;
import com.springcloud.demo.helper.CronTaskRegistrar;
import com.springcloud.demo.helper.ScheduledTask;
import com.springcloud.demo.helper.SchedulingRunnable;
import com.springcloud.demo.service.SysScheduleService;
import com.springcloud.demo.util.CronUtil;
import com.springcloud.demo.util.R;
import com.springcloud.demo.util.SpringUtil;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-schedule")
public class SysScheduleController {

    /**
     * 定时任务操作类service
     */
    private SysScheduleService sysScheduleService;

    /**
     * 定时任务注册类
     */
    private CronTaskRegistrar cronTaskRegistrar;

    /**
     * 新增定时任务
     * @param sysSchedule
     * @return
     */
    @PostMapping
    public R addJob(SysSchedule sysSchedule){

        if(ObjectUtil.isNull(sysSchedule)||ObjectUtil.isNull(sysSchedule.getScheBeanName())||ObjectUtil.isNull(sysSchedule.getScheMethod())){
            return R.failed("请正确填写完整信息");
        }

        //判空
        if(ObjectUtil.isNotNull(sysSchedule.getScheBeanName())){
            Object bean = SpringUtil.getBeanByName(sysSchedule.getScheBeanName());
            if(ObjectUtil.isNull(bean)){
                return R.failed("查无此类，请核实");
            }

            try {
                bean.getClass().getDeclaredMethod(sysSchedule.getScheMethod());
            } catch (NoSuchMethodException e) {
                return R.failed("查无此方法，请核实");
            }
        }

        //验证cron表达式合法性
        if(ObjectUtil.isNull(sysSchedule.getScheCron())||(!CronUtil.isValidExpression(sysSchedule.getScheCron()))){
            return R.failed("请输入正确cron表达式");
        }

        //判重
        if(ObjectUtil.isNotNull(this.sysScheduleService.findByBeanNameAndMethod(sysSchedule.getScheBeanName(),sysSchedule.getScheMethod()))){
            return R.failed("该定时任务已存在，请核实");
        }

        this.sysScheduleService.save(sysSchedule);

        //如果定时任务是激活状态
        if(sysSchedule.getScheStatus() == CommonConst.VALID){
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysSchedule.getScheBeanName(), sysSchedule.getScheMethod());
            cronTaskRegistrar.addCronTask(schedulingRunnable, sysSchedule.getScheCron());
        }
        return R.suc();
    }

    /**
     * 更新定时任务操作类
     * @param sysSchedule
     * @return
     */
    @PutMapping
    public R updateJob(SysSchedule sysSchedule){
        if(ObjectUtil.isNull(sysSchedule)||ObjectUtil.isNull(sysSchedule.getScheBeanName())||ObjectUtil.isNull(sysSchedule.getScheMethod())){
            return R.failed("请正确填写完整信息");
        }

        if(ObjectUtil.isNull(sysSchedule.getId())){
            return R.failed("请选择要修改的定时任务");
        }

        if(ObjectUtil.isNotNull(sysSchedule.getScheBeanName())){
            Object bean = SpringUtil.getBeanByName(sysSchedule.getScheBeanName());
            if(ObjectUtil.isNull(bean)){
                return R.failed("查无此类，请核实");
            }
            try {
                bean.getClass().getDeclaredMethod(sysSchedule.getScheMethod());
            } catch (NoSuchMethodException e) {
                return R.failed("查无此方法，请核实");
            }
        }

        //验证cron表达式合法性
        if(ObjectUtil.isNull(sysSchedule.getScheCron())||(!CronUtil.isValidExpression(sysSchedule.getScheCron()))){
            return R.failed("请输入正确cron表达式");
        }

        SysSchedule oldSysSchedule = this.sysScheduleService.getById(sysSchedule.getId());

        this.sysScheduleService.updateById(sysSchedule);

        //停止之前的定时任务
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(oldSysSchedule.getScheBeanName(), oldSysSchedule.getScheMethod());
        this.cronTaskRegistrar.removeCronTask(schedulingRunnable);

        //判断是否重新启动
        if(sysSchedule.getScheStatus() == CommonConst.VALID){
            cronTaskRegistrar.addCronTask( new SchedulingRunnable(sysSchedule.getScheBeanName(), sysSchedule.getScheMethod()), sysSchedule.getScheCron());
        }

        return R.suc();
    }

    /**
     * 删除定时任务
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R removeJob(@PathVariable Integer id){

        //从数据库获取数据
        SysSchedule schedule = this.sysScheduleService.getById(id);
        if(ObjectUtil.isNull(schedule)){
            return R.failed("查无此数据，请核实");
        }

        //停止定时任务
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(schedule.getScheBeanName(), schedule.getScheMethod());
        this.cronTaskRegistrar.removeCronTask(schedulingRunnable);

        //最后删除数据库数据
        this.sysScheduleService.removeById(id);
        return R.suc();
    }

    /**
     * 获取定时任务
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getJob(@PathVariable Integer id){
        return R.suc(this.sysScheduleService.getById(id));
    }

    /**
     * 获取定时任务列表
     * @param page
     * @param sysSchedule
     * @return
     */
    @GetMapping("/page")
    public R getPage(IPage<SysSchedule> page,SysSchedule sysSchedule){
        return R.suc(this.sysScheduleService.page(page,new QueryWrapper<SysSchedule>(sysSchedule)));
    }
}

