package com.chaojilaji.hyutils.db.model;

import java.time.LocalDateTime;
import com.chaojilaji.hyutils.dbcore.extension.like.LikeParamExtension;
import java.util.Objects;
import java.util.List;

//RBAC之api基础表
public class SysBaseApis {
    private Long id;         //记录标识 
 private List<Long> idList; //orm层需要，无实际意义
    private String method;         //HTTP方法 
    private String url;         //URL 
    private String type;         //api类型，page/api 
    private String icon;         //前端所需的icon标记 
    private String description;         //描述，用于选择权限时展示 
    private Long fatherId;         //父api的Id，主要用于给api做页面归属 
    private Long createdId;         //创建人标识 
    private String createdName;         //创建人姓名 
    private LocalDateTime createdTime;         //创建时间 
private LocalDateTime createdTimeBiggerThan; // orm层需要，无实际意义
    private LocalDateTime createdTimeLowerThan; // orm层需要，无实际意义
    private Long modifiedId;         //修改人标识 
    private String modifiedName;         //修改人姓名 
    private LocalDateTime modifiedTime;         //修改时间 
private LocalDateTime modifiedTimeBiggerThan; // orm层需要，无实际意义
    private LocalDateTime modifiedTimeLowerThan; // orm层需要，无实际意义
    private Long deletedId;         //删除人标识 
    private String deletedName;         //删除人姓名 
    private LocalDateTime deletedTime;         //删除时间 
private LocalDateTime deletedTimeBiggerThan; // orm层需要，无实际意义
    private LocalDateTime deletedTimeLowerThan; // orm层需要，无实际意义
    private Boolean deletedMark;         //删除标记 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
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
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getFatherId() {
        return fatherId;
    }
    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }
    public Long getCreatedId() {
        return createdId;
    }
    public void setCreatedId(Long createdId) {
        this.createdId = createdId;
    }
    public String getCreatedName() {
        return createdName;
    }
    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
public LocalDateTime getCreatedTimeBiggerThan() {
        return createdTimeBiggerThan;
    }

    public void setCreatedTimeBiggerThan(LocalDateTime createdTimeBiggerThan) {
        this.createdTimeBiggerThan = createdTimeBiggerThan;
    }

    public LocalDateTime getCreatedTimeLowerThan() {
        return createdTimeLowerThan;
    }

    public void setCreatedTimeLowerThan(LocalDateTime createdTimeLowerThan) {
        this.createdTimeLowerThan = createdTimeLowerThan;
    }    public Long getModifiedId() {
        return modifiedId;
    }
    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }
    public String getModifiedName() {
        return modifiedName;
    }
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }
    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }
    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
public LocalDateTime getModifiedTimeBiggerThan() {
        return modifiedTimeBiggerThan;
    }

    public void setModifiedTimeBiggerThan(LocalDateTime modifiedTimeBiggerThan) {
        this.modifiedTimeBiggerThan = modifiedTimeBiggerThan;
    }

    public LocalDateTime getModifiedTimeLowerThan() {
        return modifiedTimeLowerThan;
    }

    public void setModifiedTimeLowerThan(LocalDateTime modifiedTimeLowerThan) {
        this.modifiedTimeLowerThan = modifiedTimeLowerThan;
    }    public Long getDeletedId() {
        return deletedId;
    }
    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
    public String getDeletedName() {
        return deletedName;
    }
    public void setDeletedName(String deletedName) {
        this.deletedName = deletedName;
    }
    public LocalDateTime getDeletedTime() {
        return deletedTime;
    }
    public void setDeletedTime(LocalDateTime deletedTime) {
        this.deletedTime = deletedTime;
    }
public LocalDateTime getDeletedTimeBiggerThan() {
        return deletedTimeBiggerThan;
    }

    public void setDeletedTimeBiggerThan(LocalDateTime deletedTimeBiggerThan) {
        this.deletedTimeBiggerThan = deletedTimeBiggerThan;
    }

    public LocalDateTime getDeletedTimeLowerThan() {
        return deletedTimeLowerThan;
    }

    public void setDeletedTimeLowerThan(LocalDateTime deletedTimeLowerThan) {
        this.deletedTimeLowerThan = deletedTimeLowerThan;
    }    public Boolean getDeletedMark() {
        return deletedMark;
    }
    public void setDeletedMark(Boolean deletedMark) {
        this.deletedMark = deletedMark;
    }


public static Builder builder(){
        return new Builder();
    }

public static class Builder {
private SysBaseApis target;

public Builder() {
            this.target = new SysBaseApis();
        }

public SysBaseApis build() {
            return target;
        }public Builder id(Long id) {
            this.target.setId(id);
            return this;
        }
public Builder idNot(Long id) {
                        if (Objects.isNull(id)){
                                        this.target.setId(id);
                                        return this;
                                    }
                                    this.target.setId(-1L*id);
                                    return this;
                                }
public Builder idList(List<Long> idList) {
            this.target.setIdList(idList);
            return this;
        }
public Builder method(String method) {
            this.target.setMethod(method);
            return this;
        }
public Builder methodLike(String method) {
if (Objects.isNull(method)){
                this.target.setMethod(method);
                return this;
            }
            this.target.setMethod(LikeParamExtension.PARAM_LEFT_LIKE + method + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder methodLeftLike(String method) {
if (Objects.isNull(method)){
                this.target.setMethod(method);
                return this;
            }
            this.target.setMethod(LikeParamExtension.PARAM_LEFT_LIKE + method);
            return this;
        }

        public Builder methodRightLike(String method) {
if (Objects.isNull(method)){
                this.target.setMethod(method);
                return this;
            }
            this.target.setMethod(method + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder methodNot(String method) {
                        if (Objects.isNull(method)){
                                        this.target.setMethod(method);
                                        return this;
                                    }
                                    this.target.setMethod("-1*"+method);
                                    return this;
                                }
public Builder url(String url) {
            this.target.setUrl(url);
            return this;
        }
public Builder urlLike(String url) {
if (Objects.isNull(url)){
                this.target.setUrl(url);
                return this;
            }
            this.target.setUrl(LikeParamExtension.PARAM_LEFT_LIKE + url + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder urlLeftLike(String url) {
if (Objects.isNull(url)){
                this.target.setUrl(url);
                return this;
            }
            this.target.setUrl(LikeParamExtension.PARAM_LEFT_LIKE + url);
            return this;
        }

        public Builder urlRightLike(String url) {
if (Objects.isNull(url)){
                this.target.setUrl(url);
                return this;
            }
            this.target.setUrl(url + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder urlNot(String url) {
                        if (Objects.isNull(url)){
                                        this.target.setUrl(url);
                                        return this;
                                    }
                                    this.target.setUrl("-1*"+url);
                                    return this;
                                }
public Builder type(String type) {
            this.target.setType(type);
            return this;
        }
public Builder typeLike(String type) {
if (Objects.isNull(type)){
                this.target.setType(type);
                return this;
            }
            this.target.setType(LikeParamExtension.PARAM_LEFT_LIKE + type + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder typeLeftLike(String type) {
if (Objects.isNull(type)){
                this.target.setType(type);
                return this;
            }
            this.target.setType(LikeParamExtension.PARAM_LEFT_LIKE + type);
            return this;
        }

        public Builder typeRightLike(String type) {
if (Objects.isNull(type)){
                this.target.setType(type);
                return this;
            }
            this.target.setType(type + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder typeNot(String type) {
                        if (Objects.isNull(type)){
                                        this.target.setType(type);
                                        return this;
                                    }
                                    this.target.setType("-1*"+type);
                                    return this;
                                }
public Builder icon(String icon) {
            this.target.setIcon(icon);
            return this;
        }
public Builder iconLike(String icon) {
if (Objects.isNull(icon)){
                this.target.setIcon(icon);
                return this;
            }
            this.target.setIcon(LikeParamExtension.PARAM_LEFT_LIKE + icon + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder iconLeftLike(String icon) {
if (Objects.isNull(icon)){
                this.target.setIcon(icon);
                return this;
            }
            this.target.setIcon(LikeParamExtension.PARAM_LEFT_LIKE + icon);
            return this;
        }

        public Builder iconRightLike(String icon) {
if (Objects.isNull(icon)){
                this.target.setIcon(icon);
                return this;
            }
            this.target.setIcon(icon + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder iconNot(String icon) {
                        if (Objects.isNull(icon)){
                                        this.target.setIcon(icon);
                                        return this;
                                    }
                                    this.target.setIcon("-1*"+icon);
                                    return this;
                                }
public Builder description(String description) {
            this.target.setDescription(description);
            return this;
        }
public Builder descriptionLike(String description) {
if (Objects.isNull(description)){
                this.target.setDescription(description);
                return this;
            }
            this.target.setDescription(LikeParamExtension.PARAM_LEFT_LIKE + description + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder descriptionLeftLike(String description) {
if (Objects.isNull(description)){
                this.target.setDescription(description);
                return this;
            }
            this.target.setDescription(LikeParamExtension.PARAM_LEFT_LIKE + description);
            return this;
        }

        public Builder descriptionRightLike(String description) {
if (Objects.isNull(description)){
                this.target.setDescription(description);
                return this;
            }
            this.target.setDescription(description + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder descriptionNot(String description) {
                        if (Objects.isNull(description)){
                                        this.target.setDescription(description);
                                        return this;
                                    }
                                    this.target.setDescription("-1*"+description);
                                    return this;
                                }
public Builder fatherId(Long fatherId) {
            this.target.setFatherId(fatherId);
            return this;
        }
public Builder fatherIdNot(Long fatherId) {
                        if (Objects.isNull(fatherId)){
                                        this.target.setFatherId(fatherId);
                                        return this;
                                    }
                                    this.target.setFatherId(-1L*fatherId);
                                    return this;
                                }
public Builder createdId(Long createdId) {
            this.target.setCreatedId(createdId);
            return this;
        }
public Builder createdIdNot(Long createdId) {
                        if (Objects.isNull(createdId)){
                                        this.target.setCreatedId(createdId);
                                        return this;
                                    }
                                    this.target.setCreatedId(-1L*createdId);
                                    return this;
                                }
public Builder createdName(String createdName) {
            this.target.setCreatedName(createdName);
            return this;
        }
public Builder createdNameLike(String createdName) {
if (Objects.isNull(createdName)){
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(LikeParamExtension.PARAM_LEFT_LIKE + createdName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder createdNameLeftLike(String createdName) {
if (Objects.isNull(createdName)){
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(LikeParamExtension.PARAM_LEFT_LIKE + createdName);
            return this;
        }

        public Builder createdNameRightLike(String createdName) {
if (Objects.isNull(createdName)){
                this.target.setCreatedName(createdName);
                return this;
            }
            this.target.setCreatedName(createdName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder createdNameNot(String createdName) {
                        if (Objects.isNull(createdName)){
                                        this.target.setCreatedName(createdName);
                                        return this;
                                    }
                                    this.target.setCreatedName("-1*"+createdName);
                                    return this;
                                }
public Builder createdTime(LocalDateTime createdTime) {
            this.target.setCreatedTime(createdTime);
            return this;
        }
public Builder createdTimeBiggerThan(LocalDateTime createdTime) {
            this.target.setCreatedTimeBiggerThan(createdTime);
            return this;
        }

public Builder createdTimeLowerThan(LocalDateTime createdTime) {
            this.target.setCreatedTimeLowerThan(createdTime);
            return this;
        }

public Builder modifiedId(Long modifiedId) {
            this.target.setModifiedId(modifiedId);
            return this;
        }
public Builder modifiedIdNot(Long modifiedId) {
                        if (Objects.isNull(modifiedId)){
                                        this.target.setModifiedId(modifiedId);
                                        return this;
                                    }
                                    this.target.setModifiedId(-1L*modifiedId);
                                    return this;
                                }
public Builder modifiedName(String modifiedName) {
            this.target.setModifiedName(modifiedName);
            return this;
        }
public Builder modifiedNameLike(String modifiedName) {
if (Objects.isNull(modifiedName)){
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(LikeParamExtension.PARAM_LEFT_LIKE + modifiedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder modifiedNameLeftLike(String modifiedName) {
if (Objects.isNull(modifiedName)){
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(LikeParamExtension.PARAM_LEFT_LIKE + modifiedName);
            return this;
        }

        public Builder modifiedNameRightLike(String modifiedName) {
if (Objects.isNull(modifiedName)){
                this.target.setModifiedName(modifiedName);
                return this;
            }
            this.target.setModifiedName(modifiedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder modifiedNameNot(String modifiedName) {
                        if (Objects.isNull(modifiedName)){
                                        this.target.setModifiedName(modifiedName);
                                        return this;
                                    }
                                    this.target.setModifiedName("-1*"+modifiedName);
                                    return this;
                                }
public Builder modifiedTime(LocalDateTime modifiedTime) {
            this.target.setModifiedTime(modifiedTime);
            return this;
        }
public Builder modifiedTimeBiggerThan(LocalDateTime modifiedTime) {
            this.target.setModifiedTimeBiggerThan(modifiedTime);
            return this;
        }

public Builder modifiedTimeLowerThan(LocalDateTime modifiedTime) {
            this.target.setModifiedTimeLowerThan(modifiedTime);
            return this;
        }

public Builder deletedId(Long deletedId) {
            this.target.setDeletedId(deletedId);
            return this;
        }
public Builder deletedIdNot(Long deletedId) {
                        if (Objects.isNull(deletedId)){
                                        this.target.setDeletedId(deletedId);
                                        return this;
                                    }
                                    this.target.setDeletedId(-1L*deletedId);
                                    return this;
                                }
public Builder deletedName(String deletedName) {
            this.target.setDeletedName(deletedName);
            return this;
        }
public Builder deletedNameLike(String deletedName) {
if (Objects.isNull(deletedName)){
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(LikeParamExtension.PARAM_LEFT_LIKE + deletedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }

        public Builder deletedNameLeftLike(String deletedName) {
if (Objects.isNull(deletedName)){
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(LikeParamExtension.PARAM_LEFT_LIKE + deletedName);
            return this;
        }

        public Builder deletedNameRightLike(String deletedName) {
if (Objects.isNull(deletedName)){
                this.target.setDeletedName(deletedName);
                return this;
            }
            this.target.setDeletedName(deletedName + LikeParamExtension.PARAM_RIGHT_LIKE);
            return this;
        }
public Builder deletedNameNot(String deletedName) {
                        if (Objects.isNull(deletedName)){
                                        this.target.setDeletedName(deletedName);
                                        return this;
                                    }
                                    this.target.setDeletedName("-1*"+deletedName);
                                    return this;
                                }
public Builder deletedTime(LocalDateTime deletedTime) {
            this.target.setDeletedTime(deletedTime);
            return this;
        }
public Builder deletedTimeBiggerThan(LocalDateTime deletedTime) {
            this.target.setDeletedTimeBiggerThan(deletedTime);
            return this;
        }

public Builder deletedTimeLowerThan(LocalDateTime deletedTime) {
            this.target.setDeletedTimeLowerThan(deletedTime);
            return this;
        }

public Builder deletedMark(Boolean deletedMark) {
            this.target.setDeletedMark(deletedMark);
            return this;
        }


}
}