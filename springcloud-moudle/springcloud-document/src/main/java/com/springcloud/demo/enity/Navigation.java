package com.springcloud.demo.enity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

/**
 * @description：栏目实体类，对应navigation表
 * @auther: lai.guanfu
 * @date: 2019-04-08 10:23
 */
@Data
@ToString
@TableName("navigation")
public class Navigation extends BaseEntity{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 栏目名
     */
    private String navName;

    /**
     * 逻辑删除
     */
    @TableLogic(delval = "1")
    private Integer delFlag;
}
