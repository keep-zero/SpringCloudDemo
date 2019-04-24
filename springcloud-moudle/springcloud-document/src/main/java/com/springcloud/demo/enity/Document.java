package com.springcloud.demo.enity;



import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:  文档实体类，对应document表
 * @author: lai.guanfu
 * @date: 2019-04-07 16:07:59
 */
@Data
@ToString
@TableName(value = "document")
public class Document implements Serializable {

    /**
     * id
     */
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 逻辑删除
     */
//    @TableLogic
    private Integer delFlag;

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
}
