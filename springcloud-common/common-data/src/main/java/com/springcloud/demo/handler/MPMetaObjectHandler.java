package com.springcloud.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description mybatisplus自定义填充公共字段 ,即没有传的字段自动填充
 * @auther: lai.guanfu
 * @date: 2019-04-23 19:38
 */
@Component
public class MPMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充
     * @param metaObject 目标对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        Object createBy = metaObject.getValue("createBy");
        Object createTime = metaObject.getValue("createTime");
        //设置当前登录用户
        if (null == createBy) {
            this.setFieldValByName("createBy", "admin",metaObject);
        }
        if (null == createTime) {
            this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }
        this.updateFill(metaObject);
    }

    /**
     * 更新填充
     * @param metaObject 目标对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateBy = metaObject.getValue("updateBy");
        Object updateTime = metaObject.getValue("updateTime");
        //设置当前登录用户
        if (null == updateBy) {
            this.setFieldValByName("updateBy", "admin",metaObject);
        }
        if (null == updateTime) {
            this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        }
    }
}