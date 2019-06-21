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
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("client_total")
@ApiModel(value="ClientTotal对象", description="")
public class ClientTotal extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键记录")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "路径下的客户端总数")
    @TableField("client_total")
    private Integer clientTotal;

    @ApiModelProperty(value = "客户端路径")
    @TableField("client_path")
    private String clientPath;

    @ApiModelProperty(value = "逻辑删除")
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;
}
