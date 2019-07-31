package com.springcloud.demo.config;

import cn.hutool.core.util.ObjectUtil;
import com.springcloud.demo.entity.SysSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @description 系统线程配置
 * @auther: lai.guanfu
 * @date: 2019-06-24 15:59
 */
@Slf4j
@Configuration
public class ScheduleConfig {

    /**
     * 定时任务线程池对象
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //定时任务执行线程池核心线程数
        threadPoolTaskScheduler.setPoolSize(6);

        //允许移除任务
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);

        //线程池前缀
        threadPoolTaskScheduler.setThreadNamePrefix("TestThread-");

        return threadPoolTaskScheduler;
    }

}
