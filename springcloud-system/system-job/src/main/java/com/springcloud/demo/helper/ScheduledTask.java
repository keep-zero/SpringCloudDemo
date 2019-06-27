package com.springcloud.demo.helper;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

import java.util.concurrent.ScheduledFuture;

/**
 * @description ScheduledFuture包装类
 * @auther: lai.guanfu
 * @date: 2019-06-26 13:57
 */
public final class ScheduledTask {

    static ScheduledFuture<?> future;

    /**
     * 定时任务取消
     */
    public void cancel(){
        ScheduledFuture<?> future = this.future;
        if(ObjectUtil.isNotNull(future)){
            future.cancel(true);
        }
    }


}
