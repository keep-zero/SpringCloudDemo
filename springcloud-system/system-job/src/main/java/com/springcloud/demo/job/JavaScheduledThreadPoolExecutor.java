package com.springcloud.demo.job;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-07-30 14:02
 */
public class JavaScheduledThreadPoolExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date()+": This is my job...");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

}
