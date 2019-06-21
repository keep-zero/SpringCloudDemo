package com.springcloud.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

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
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("client")
@ApiModel(value = "Client",description = "客户端实体")
public class Client extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "客户端id",dataType = "Integer",name = "id",required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称",dataType = "String ",name = "clientName",required = true)
    @TableField("client_name")
    private String clientName;

    /**
     * 客户端路径
     */
    @ApiModelProperty(value = "客户端路径",dataType = "String ",name = "clientPath",required = true)
    @TableField("client_path")
    private String clientPath;

    /**
     * 逻辑删除标志
     */
    @ApiModelProperty(value = "逻辑删除标志",dataType = "Integer ",name = "delFlag",required = true)
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;
}
