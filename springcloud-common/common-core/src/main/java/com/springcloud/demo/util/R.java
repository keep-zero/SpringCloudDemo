package com.springcloud.demo.util;



import com.springcloud.demo.enums.RespEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * REST API 返回结果
 * @auther: lai.guanfu
 * @date: 2019-05-24 14:05
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {

    /**
     * 返回业务码
     */
    private long code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    public R() {
        // to do nothing
    }

    /**
     *  封装返回信息
     * @param data 结果集
     * @param resp 响应结果（状态码及响应信息）
     * @param <T>
     * @return
     */
    public static <T> R<T> restResult(T data, RespEnum resp) {
        return restResult(data, resp.getCode(), resp.getMsg());
    }

    /**
     *  封装返回信息（无返回结果集）
     * @param resp 响应结果
     * @param <T>
     * @return
     */
    public static <T> R<T> restResult(RespEnum resp) {
        return restResult(null, resp.getCode(), resp.getMsg());
    }

    /**
     * 封装返回信息（全信息）
     * @param data 结果集
     * @param code 响应码
     * @param msg 响应信息
     * @param <T>
     * @return
     */
    private static <T> R<T> restResult(T data, long code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    /**
     * 操作成功
     * @param <T>
     * @return
     */
    public static <T> R<T> suc() {
        return restResult(RespEnum.SUCCESS);
    }

    /**
     * 操作成功并返回结果集
     * @param data 结果集
     * @param <T>
     * @return
     */
    public static <T> R<T> suc(T data) {
        return restResult(data,RespEnum.SUCCESS);
    }

    /**
     * 操作失败并指定失败信息
     * @param msg 响应信息
     * @param <T>
     * @return
     */
    public static <T> R<T> failed(String msg){
        return restResult(null,RespEnum.FAILED.getCode(),msg);
    }

    /**
     * 操作失败并指定失败信息
     * @param respEnum 响应枚举
     * @param <T>
     * @return
     */
    public static <T> R<T> failed(RespEnum respEnum){
        return restResult(null,respEnum.getCode(),respEnum.getMsg());
    }

    /**
     * 操作失败并返回报错信息
     * @param e 报错信息对象
     * @param <T>
     * @return
     */
    public static <T> R<T> failed(Throwable e){
        return restResult(null,RespEnum.FAILED.getCode(),e.getMessage());
    }
}