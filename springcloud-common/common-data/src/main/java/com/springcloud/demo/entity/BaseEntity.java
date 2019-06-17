package com.springcloud.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description 系统内所有实体类的父类，可以帮助实现创建者及创建时间或最后更新人及最后更新时间的填充
 *              注：@Data注解不可以放在此处，如果放在此处会导致子类无法set/get属性
 * @auther: lai.guanfu
 * @date: 2019-04-08 10:45
 */
@Data //在此处使用@Data注解是因为如果不使用该注解，子类在JSON转换时会缺失父类的字段
public class BaseEntity implements Serializable{

    /**
     * 创建者
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 最后更新人
     */
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 改写toString方法，解决格式lombok继承后，子类toString方法输出格式问题
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
