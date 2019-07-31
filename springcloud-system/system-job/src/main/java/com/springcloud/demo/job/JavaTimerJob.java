package com.springcloud.demo.job;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-07-30 11:27
 */
public class JavaTimerJob {

    public static void main(String[] args) {
        Timer timer = new Timer();
        Task task = new Task();
        //当前时间开始，每1秒执行一次
        timer.schedule(task, new Date(),1000);
    }
 }

class Task extends TimerTask {

    @Override
    public void run() {
        System.out.println(new Date()+":  This is my job...");
    }
}
