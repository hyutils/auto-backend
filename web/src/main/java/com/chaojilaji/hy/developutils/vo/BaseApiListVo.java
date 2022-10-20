package com.chaojilaji.hy.developutils.vo;

public class BaseApiListVo {
    private String id;
    private String method;
    private String url;
    private String type;
    private String description;
    private String fatherName;
    private String belongFuncApis;
    private String createdTime;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private BaseApiListVo target;

        public Builder() {
            this.target = new BaseApiListVo();
        }

        public BaseApiListVo build() {
            return target;
        }public Builder fatherName(String fatherName) {
            this.target.fatherName=fatherName;
            return this;
        }
        public Builder belongFuncApis(String belongFuncApis) {
            this.target.belongFuncApis=belongFuncApis;
            return this;
        }
        public Builder method(String method) {
            this.target.method=method;
            return this;
        }
        public Builder description(String description) {
            this.target.description=description;
            return this;
        }
        public Builder createdTime(String createdTime) {
            this.target.createdTime=createdTime;
            return this;
        }
        public Builder id(String id) {
            this.target.id=id;
            return this;
        }
        public Builder type(String type) {
            this.target.type=type;
            return this;
        }
        public Builder url(String url) {
            this.target.url=url;
            return this;
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBelongFuncApis() {
        return belongFuncApis;
    }

    public void setBelongFuncApis(String belongFuncApis) {
        this.belongFuncApis = belongFuncApis;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
