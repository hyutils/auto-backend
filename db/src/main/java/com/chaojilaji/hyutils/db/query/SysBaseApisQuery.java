package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.SysBaseApis;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  SysBaseApisQuery extends PostgreSQLBaseQuery<SysBaseApis> {
    public SysBaseApisQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(SysBaseApis sysBaseApis) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(sysBaseApis)));
    }

    public Long count(List<Triplet<String, String, Object>> sysBaseApis) {
        return this.countModelByOperateSimpleAnd(sysBaseApis);
    }

    public List<SysBaseApis> page(SysBaseApis sysBaseApis, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(sysBaseApis)), page, size);
    }
    
    public Integer updateByCondition(SysBaseApis condition, SysBaseApis value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<SysBaseApis> findByCondition(SysBaseApis condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }

// TODO: 2021/12/29 数据组相关的
    public List<SysBaseApis> page(SysBaseApis sysBaseApis, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(sysBaseApis)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(SysBaseApis sysBaseApis, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(sysBaseApis)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(SysBaseApis condition, List<Long> orgIds, SysBaseApis value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }



    public List<SysBaseApis> findByCondition(SysBaseApis condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<SysBaseApis> findByCondition(SysBaseApis condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<SysBaseApis> page(SysBaseApis condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(SysBaseApis condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(SysBaseApis condition, List<Long> orgIds, Long userId, SysBaseApis value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }


}