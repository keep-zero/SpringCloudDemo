package com.springcloud.demo.init;

import com.springcloud.demo.constant.CommonConst;
import com.springcloud.demo.entity.SysSchedule;
import com.springcloud.demo.helper.CronTaskRegistrar;
import com.springcloud.demo.helper.SchedulingRunnable;
import com.springcloud.demo.service.SysScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description 项目启动执行的类
 * @auther: lai.guanfu
 * @date: 2019-06-27 12:57
 */
@Slf4j
@Component
public class InitApplication implements ApplicationRunner {

    @Autowired
    private SysScheduleService sysScheduleService;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<SysSchedule> list = sysScheduleService.list();

        for(SysSchedule sysSchedule: list){
            if(sysSchedule.getScheStatus() == CommonConst.VALID){
                log.debug("注册定时任务：{}", sysSchedule);
                SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysSchedule.getScheBeanName(), sysSchedule.getScheMethod());
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysSchedule.getScheCron());
            }
        }
    }
}
