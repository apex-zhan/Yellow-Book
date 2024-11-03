package com.litblue.starter.core;

import lombok.Data;

/**
 * Ajax请求结果封装类
 * 用于封装异步请求的响应结果，提供统一的数据格式
 *
 * @param <T> 泛型参数，用于封装具体的数据类型
 */
@Data
public class AjaxResult<T> {

    /**
     * 响应数据
     */
    private T data;

    /**
     * 消息文本，用于描述响应结果
     */
    private String msg;

    /**
     * 响应代码，用于标识响应状态
     */
    private String code;

    /**
     * 构造方法，用于创建不含数据的AjaxResult对象
     *
     * @param msg  消息文本
     * @param code 响应代码
     */
    public AjaxResult(String msg, String code) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造方法，用于创建包含数据的AjaxResult对象
     *
     * @param msg  消息文本
     * @param code 响应代码
     * @param data 响应数据
     */
    public AjaxResult(String msg, String code, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
