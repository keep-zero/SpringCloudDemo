package com.springcloud.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springcloud.demo.constant.SerializableConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @description 菜单信息
 * @auther: lai.guanfu
 * @date: 2019-06-04 22:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity{

    private static final long serialVersionUID = SerializableConst.serialVersionUID;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单父编码
     */
    @TableField(value = "p_code")
    private String pCode;

    /**
     * 父菜单ID
     */
    @TableField(value = "p_id")
    private String pId;

    /**
     * 名称
     */
    private String name;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 是否是菜单
     */
    @TableField(value = "is_menu")
    private Integer isMenu;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 状态，是否可用
     */
    private Integer status;

    /**
     * 逻辑删除标识
     */
    private Integer delFlag;
}
