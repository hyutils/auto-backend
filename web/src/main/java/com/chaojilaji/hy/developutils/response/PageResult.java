package com.chaojilaji.hy.developutils.response;

public class PageResult<T> extends BaseResult<T> {

    private Integer total;
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }

}
