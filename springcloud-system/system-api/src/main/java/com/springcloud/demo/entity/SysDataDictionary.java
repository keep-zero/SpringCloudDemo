package com.springcloud.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数据字典对象
 *
 * @author lai.guanfu
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysDataDictionary对象")
public class SysDataDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "字段")
    private String columnName;

    @ApiModelProperty(value = "字段类型")
    private String dataType;

    @ApiModelProperty(value = "字段描述")
    private String columnComment;

    @ApiModelProperty(value = "可否为空")
    private String isNullable;

    @ApiModelProperty(value = "字段长度")
    private Double length;

    @ApiModelProperty(value = "字段默认值")
    private String columnDefault;


}
