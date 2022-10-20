package com.chaojilaji.hy.developutils.query;

public class EditBaseAPi {
    private String method;
    private String path;
    private String fatherDescription;
    private String fatherPath;
    private String type;
    private String description;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private EditBaseAPi target;

        public Builder() {
            this.target = new EditBaseAPi();
        }

        public EditBaseAPi build() {
            return target;
        }public Builder path(String path) {
            this.target.path=path;
            return this;
        }
        public Builder method(String method) {
            this.target.method=method;
            return this;
        }
        public Builder fatherDescription(String fatherDescription) {
            this.target.fatherDescription=fatherDescription;
            return this;
        }
        public Builder fatherPath(String fatherPath) {
            this.target.fatherPath=fatherPath;
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

    public String getFatherDescription() {
        return fatherDescription;
    }

    public void setFatherDescription(String fatherDescription) {
        this.fatherDescription = fatherDescription;
    }

    public String getFatherPath() {
        return fatherPath;
    }

    public void setFatherPath(String fatherPath) {
        this.fatherPath = fatherPath;
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
