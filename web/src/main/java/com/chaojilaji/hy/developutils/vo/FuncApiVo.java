package com.chaojilaji.hy.developutils.vo;

public class FuncApiVo {

    private String id;
    private String name;
    private String baseApiNames;
    private String description;
    private String fatherName;
    private String createdTime;
    private String type;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FuncApiVo target;

        public Builder() {
            this.target = new FuncApiVo();
        }

        public FuncApiVo build() {
            return target;
        }

        public Builder fatherName(String fatherName) {
            this.target.fatherName = fatherName;
            return this;
        }

        public Builder name(String name) {
            this.target.name = name;
            return this;
        }

        public Builder type(String type) {
            this.target.type = type;
            return this;
        }

        public Builder description(String description) {
            this.target.description = description;
            return this;
        }

        public Builder createdTime(String createdTime) {
            this.target.createdTime = createdTime;
            return this;
        }

        public Builder id(String id) {
            this.target.id = id;
            return this;
        }

        public Builder baseApiNames(String baseApiNames) {
            this.target.baseApiNames = baseApiNames;
            return this;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseApiNames() {
        return baseApiNames;
    }

    public void setBaseApiNames(String baseApiNames) {
        this.baseApiNames = baseApiNames;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
