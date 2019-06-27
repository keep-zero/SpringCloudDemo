package com.springcloud.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description mybatisplus自定义填充公共字段 ,即没有传的字段自动填充
 * @auther: lai.guanfu
 * @date: 2019-04-23 19:38
 */
@Component
public class MPMetaObjectHandler implements MetaObjectHandler {

    /**
     * 获取逻辑删除的未删除值作为默认初始值
     */
    @Value(value = "${mybatis-plus.global-config.db-config.logic-not-delete-value}")
    private Integer delFlagDefaultValue=0;

    /**
     * 插入填充
     * @param metaObject 目标对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        //判断是否存在该字段
        if ( metaObject.hasGetter("createBy")) {
            this.setFieldValByName("createBy", "admin",metaObject);
        }

        if (metaObject.hasGetter("createTime")) {
            this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }

        if (metaObject.hasGetter("delFlag")) {
            this.setFieldValByName("delFlag", delFlagDefaultValue,metaObject);
        }

        this.updateFill(metaObject);
    }

    /**
     * 更新填充
     * @param metaObject 目标对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //判断是否存在该字段
        if ( metaObject.hasGetter("updateBy")) {
            this.setFieldValByName("updateBy", "admin",metaObject);
        }

        if (metaObject.hasGetter("updateTime")) {
            this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        }
    }
}