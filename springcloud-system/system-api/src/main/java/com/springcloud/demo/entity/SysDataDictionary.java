package com.springcloud.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据字典对象
 *
 * @author lai.guanfu
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_data_dictionary")
@ApiModel(value="SysDataDictionary对象", description="")
public class SysDataDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表名")
    @TableField("table_name")
    private String tableName;

    @ApiModelProperty(value = "字段")
    @TableField("COLUMN_NAME")
    private String columnName;

    @ApiModelProperty(value = "字段类型")
    @TableField("DATA_TYPE")
    private String dataType;

    @ApiModelProperty(value = "字段描述")
    @TableField("COLUMN_COMMENT")
    private String columnComment;

    @ApiModelProperty(value = "可否为空")
    @TableField("IS_NULLABLE")
    private String isNullable;

    @ApiModelProperty(value = "字段长度")
    @TableField("LENGTH")
    private Double length;

    @ApiModelProperty(value = "字段默认值")
    @TableField("COLUMN_DEFAULT")
    private String columnDefault;


}
