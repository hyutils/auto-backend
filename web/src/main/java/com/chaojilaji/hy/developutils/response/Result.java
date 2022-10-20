package com.chaojilaji.hy.developutils.response;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(ResultStatus resultStatus, T obj) {
        Result<T> result = new Result<T>();
        result.setData(obj);
        result.setCode(resultStatus.getCode());
        result.setMsg(resultStatus.getMessage());
        return result;
    }

    public static <T> Result<T> success(T obj) {
        Result<T> result = new Result<T>();
        result.setData(obj);
        result.setCode(CommonResultStatus.OK.getCode());
        result.setMsg(CommonResultStatus.OK.getMessage());
        return result;
    }


    public static <T> Result<T> failure(String msg,T obj){
        Result<T> result = new Result<T>();
        result.setMsg(msg);

        result.setCode(CommonResultStatus.IMPORT_FAIL.getCode());
        result.setData(obj);
        return result;
    }

    public static <T> Result<T> failure1(CommonResultStatus commonResultStatus,T obj){
        Result<T> result = new Result<T>();
        result.setMsg(commonResultStatus.getMessage());

        result.setCode(commonResultStatus.getCode());
        result.setData(obj);
        return result;
    }


    public static Result failure(String resultStatus) {
        Result result = new Result();
        result.setMsg(resultStatus);
        return result;
    }

    public static Result failure(ResultStatus resultStatus) {
        Result result = new Result();
        result.setMsg(resultStatus.getMessage());
        result.setCode(resultStatus.getCode());
        return result;
    }

    public static Result failure(ResultStatus resultStatus, String message) {
        Result result = new Result();
        result.setCode(resultStatus.getCode());
        result.setMsg(message);
        return result;
    }
}
