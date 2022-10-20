package com.chaojilaji.hy.developutils.response;

import java.io.Serializable;

public class ResultV2<T> implements Serializable {
    private Integer code;
    private String msg;
    private BaseResult<T> result;

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

    public BaseResult<T> getResult() {
        return result;
    }

    public void setResult(BaseResult<T> result) {
        this.result = result;
    }

    public static <T> ResultV2<T> success(T obj) {
        ResultV2<T> result = new ResultV2<T>();
        BaseResult<T> data = new BaseResult<>();
        data.setData(obj);
        result.setResult(data);
        result.setCode(CommonResultStatus.OK.getCode());
        result.setMsg(CommonResultStatus.OK.getMessage());
        return result;
    }

    public static <T> ResultV2<T> success(T obj,Integer total) {
        ResultV2<T> result = new ResultV2<T>();
        PageResult<T> data = new PageResult<>();
        data.setData(obj);
        data.setTotal(total);
        result.setResult(data);
        result.setCode(CommonResultStatus.OK.getCode());
        result.setMsg(CommonResultStatus.OK.getMessage());
        return result;
    }






    public static ResultV2 failure(String resultStatus) {
        ResultV2 result = new ResultV2();
        result.setMsg(resultStatus);
        return result;
    }

    public static ResultV2 failure(ResultStatus resultStatus) {
        ResultV2 result = new ResultV2();
        result.setMsg(resultStatus.getMessage());
        result.setCode(resultStatus.getCode());
        return result;
    }

    public static ResultV2 failure(ResultStatus resultStatus, String message) {
        ResultV2 result = new ResultV2();
        result.setCode(resultStatus.getCode());
        result.setMsg(message);
        return result;
    }
}
