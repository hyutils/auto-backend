package com.chaojilaji.hy.developutils.response;

public class BaseResult<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
