package com.springcloud.demo.enity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @description： 文档和栏目的映射表，一对多关系，对应doc_nav_mapping表
 * @auther: lai.guanfu
 * @date: 2019-04-08 10:58
 */
@Data
@ToString
@TableName("doc_nav_mapping")
public class DocNavMapping extends BaseEntity{

    @TableId(type=IdType.AUTO)
    private Integer id;

    /**
     * 文档id
     */
    private Integer docId;

    /**
     * 栏目id
     */
    private Integer navId;

    @TableLogic(delval = "1")
    private Integer delFlag;
}
