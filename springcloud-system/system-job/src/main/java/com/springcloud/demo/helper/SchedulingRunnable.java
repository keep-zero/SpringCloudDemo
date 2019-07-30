package com.springcloud.demo.helper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.springcloud.demo.util.SpringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @description 定时任务执行线程实现类，引自https://www.jianshu.com/p/0f68936393fd?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 * @auther: lai.guanfu
 * @date: 2019-06-26 14:00
 */
@Slf4j
@AllArgsConstructor
@Getter
public class SchedulingRunnable implements Runnable {

    /**
     * 定时任务指定Bean类名
     */
    private String scheBeanName;

    /**
     * 定时任务指定Bean的方法名
     */
    private String scheMethod;


    //执行指定bean里面的方法。
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        if(log.isDebugEnabled()){
            log.debug("执行对应的bean方法：{}.{}(),当前时间：{}",scheBeanName,scheMethod,new Date());
        }

        //利用反射生成对应的bean实例并执行指定方法,指定bean可能涉及数据库或者Spring体系的内容，所以统一从spring容器中获取
        Object target = SpringUtil.getBeanByName(this.getScheBeanName());
        Method method = null;

        //执行实例判空
        if(ObjectUtil.isNotNull(target)){
            try {
                //获取指定方法
                method = target.getClass().getDeclaredMethod(scheMethod);
            } catch (NoSuchMethodException e) {
                if (log.isErrorEnabled()){
                    log.error(e.getMessage());
                }
            }

            if(ObjectUtil.isNotNull(method)){
                //使该方法可访问可执行
                ReflectionUtils.makeAccessible(method);
                try {
                    method.invoke(target,null );
                } catch (IllegalAccessException e) {

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            if(log.isDebugEnabled()) {
                log.debug("方法执行完毕，耗时：{}", System.currentTimeMillis()-startTime);
            }
        }
    }

    /**
     * 重写equals方法，否则移除定时任务将出现无法移除的情况，因为无法匹配
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        return scheBeanName.equals(that.scheBeanName) &&
                scheMethod.equals(that.getScheMethod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheBeanName, scheMethod);
    }
}
