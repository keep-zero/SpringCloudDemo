package com.springcloud.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springcloud.demo.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_schedule")
@ApiModel(value="SysSchedule对象", description="定时任务操作类")
public class SysSchedule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "定时任务类全路径")
    @TableField("sche_bean_name")
    private String scheBeanName;

    @ApiModelProperty(value = "定时任务类执行方法，默认使用run（）方法")
    @TableField("sche_method")
    private String scheMethod;

    @ApiModelProperty(value = "定时任务执行规则cron表达式")
    @TableField("sche_cron")
    private String scheCron;

    @ApiModelProperty(value = "定时任务描述")
    @TableField("sche_desc")
    private String scheDesc;

    @ApiModelProperty(value = "定时任务状态")
    @TableField("sche_status")
    private Integer scheStatus;

    @ApiModelProperty(value = "是否删除")
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;
}
