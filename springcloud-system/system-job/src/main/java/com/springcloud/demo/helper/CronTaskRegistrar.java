package com.springcloud.demo.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description 定时任务注册类,用于增加删除定时任务类
 * @auther: lai.guanfu
 * @date: 2019-06-26 15:04
 */
@Slf4j
@Component
public class CronTaskRegistrar implements DisposableBean {

    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    public void addCronTask(Runnable task, String cronExpression) {
        addCronTask(new CronTask(task, cronExpression));
    }

    public void addCronTask(CronTask cronTask) {
        if(log.isDebugEnabled()){
            log.debug("注册新定时任务");
        }
        if (cronTask != null) {
            Runnable task = cronTask.getRunnable();
            if (this.scheduledTasks.containsKey(task)) {
                removeCronTask(task);
            }

            this.scheduledTasks.put(task, scheduleCronTask(cronTask));
        }
    }

    public void removeCronTask(Runnable task) {
        if(log.isDebugEnabled()){
            log.debug("移除定时任务");
        }
        boolean b = this.scheduledTasks.containsKey(task);
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (scheduledTask != null)
            scheduledTask.cancel();
    }

    public ScheduledTask scheduleCronTask(CronTask cronTask) {
        if(log.isDebugEnabled()){
            log.debug("构建定时任务");
        }
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }


    @Override
    public void destroy() {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        this.scheduledTasks.clear();
    }
}
