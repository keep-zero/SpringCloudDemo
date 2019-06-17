package com.springcloud.demo.enums;

/**
 * @description api返回的基本状态码及反馈信息封装枚举
 * @auther: lai.guanfu
 * @date: 2019-05-24 15:07
 */
public enum RespEnum {
    /**
     * 成功
     */
    SUCCESS(0,"操作成功"),

    /**
     * 失败
     */
    FAILED(1, "操作失败"),


    /**
     * 未登录
     */
    NO_LOGIN(10,"未登录"),

    /**
     * 无权限
     */
    NO_PERMISSION(12,"无权限操作");

    private final long code;
    private final String msg;

    RespEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RespEnum fromCode(long code) {
        RespEnum[] ecs = values();
        for (RespEnum ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }


    /**
     * 状态码
     * @return
     */
    public long getCode(){
        return code;
    };

    /**
     * 描述
     */
    public String getMsg(){
        return msg;
    };

    @Override
    public String toString() {
        return String.format(" RespEnum:{code=%s, msg=%s} ", code, msg);
    }
}
