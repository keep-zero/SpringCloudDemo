package com.springcloud.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springcloud.demo.constant.SerializableConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @description 角色信息表
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends BaseEntity{

    private static final long serialVersionUID = SerializableConst.serialVersionUID;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色代值
     */
    private String value;

    /**
     * 角色描述
     */
    private String tips;

    /**
     * 状态，是否可用
     */
    private Integer status;

    /**
     * 逻辑删除标识
     */
    private Integer delFlag;
}
