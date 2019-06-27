package com.springcloud.demo.util;

import lombok.experimental.UtilityClass;
import org.springframework.scheduling.support.CronSequenceGenerator;

/**
 * @description cron表达式工具
 * @auther: lai.guanfu
 * @date: 2019-06-26 15:29
 */
@UtilityClass
public class CronUtil {

    /**
     * 验证cron表达式是否正确
     * @param corn
     * @return
     */
    public boolean isValidExpression(String corn){
        //使用spring内置工具CronSequenceGenerator进行校验
        return CronSequenceGenerator.isValidExpression(corn);
    }



}
