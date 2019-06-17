package com.springcloud.demo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.springcloud.demo.constant.SerializableConst;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-05 9:43
 */
@Data
public class SysRoleVo {

    private static final long serialVersionUID = SerializableConst.serialVersionUID;

    @TableId
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
     *  创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     *  最后更新人
     */
    private String updateBy;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
}
