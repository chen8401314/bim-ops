package com.common.service.dto;

import com.example.common.exception.MessageEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 该接口是一个泛型类接口 该接口必须实现序列化接口的实体类作为泛型实际参数
 *
 * @param <T>
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -8524609179308474508L;

    public static final String DEFAULT_MESSAGE_SUCCESS = "操作成功";

    public static final int DEFAULT_CODE_SUCCESS = 200;

    public static final int DEFAULT_CODE_FAILURE = 500;

    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private T data;
    @Getter
    @Setter
    private LocalDateTime timestamp = LocalDateTime.now();


    public Response() {
    }

    private Response(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 系统默认成功
     *
     * @param data
     * @return
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(DEFAULT_CODE_SUCCESS, DEFAULT_MESSAGE_SUCCESS, data);
    }

    /**
     * 系统默认成功
     *
     * @return
     */
    public static <T> Response<T> success() {
        return new Response<>(DEFAULT_CODE_SUCCESS, DEFAULT_MESSAGE_SUCCESS);
    }

    /**
     * 系统默认失败 ,失败并设置message
     */
    public static <T> Response<T> failure(String message) {
        return new Response<>(DEFAULT_CODE_FAILURE, message);
    }

    /**
     * 系统默认失败
     *
     * @param message
     * @return
     */
    public static <T> Response<T> failure(int code, String message) {
        return new Response<>(code, message);
    }

    /**
     * 请求成功
     *
     * @param code
     * @param message
     * @return
     */
    public static <T> Response<T> success(int code, String message) {
        return new Response<>(code, message);
    }

    /**
     * 用户自定义成功
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> Response<T> success(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    /**
     * 用户自定义失败
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> Response<T> failure(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    public static <T> Response<T> failure(MessageEnum msg) {
        return new Response<>(msg.getCode(), msg.getMessage());
    }


    public boolean valid() {
        return this.code == DEFAULT_CODE_SUCCESS;
    }
}
