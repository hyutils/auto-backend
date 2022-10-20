package com.chaojilaji.hy.developutils.query;

public class AddBaseApi {
    private String method;
    private String path;
    private String fatherName;
    private String type;
    private String description;
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private AddBaseApi target;

        public Builder() {
            this.target = new AddBaseApi();
        }

        public AddBaseApi build() {
            return target;
        }public Builder path(String path) {
            this.target.path=path;
            return this;
        }
        public Builder fatherName(String fatherName) {
            this.target.fatherName=fatherName;
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
        public Builder type(String type) {
            this.target.type=type;
            return this;
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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
}
