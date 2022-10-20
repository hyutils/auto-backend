package com.chaojilaji.hyutils.db.query;

import com.chaojilaji.hyutils.db.model.SysFuncApis;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import com.chaojilaji.hyutils.dbcore.instance.PostgreSQLBaseQuery;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class  SysFuncApisQuery extends PostgreSQLBaseQuery<SysFuncApis> {
    public SysFuncApisQuery() {
        super();
        this.primaryKey = "id";
        this.fieldOrgName = "created_org_id";
    }

    public Long count(SysFuncApis sysFuncApis) {
        return this.countModelBySimpleAnd(Json.toMap(Json.toJson(sysFuncApis)));
    }

    public Long count(List<Triplet<String, String, Object>> sysFuncApis) {
        return this.countModelByOperateSimpleAnd(sysFuncApis);
    }

    public List<SysFuncApis> page(SysFuncApis sysFuncApis, Integer page, Integer size) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(sysFuncApis)), page, size);
    }
    
    public Integer updateByCondition(SysFuncApis condition, SysFuncApis value) {
        return this.update(Json.toMap(Json.toJson(condition)), Json.toMap(Json.toJson(value)));
    }
    public List<SysFuncApis> findByCondition(SysFuncApis condition) {
        return this.findListModelBySimpleAnd(Json.toMap(Json.toJson(condition)));
    }

// TODO: 2021/12/29 数据组相关的
    public List<SysFuncApis> page(SysFuncApis sysFuncApis, List<Long> orgIds, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(sysFuncApis)), orgIds, this.fieldOrgName), page, size);
    }

    public Long count(SysFuncApis sysFuncApis, List<Long> orgIds) {
        return this.countModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(sysFuncApis)), orgIds, this.fieldOrgName));
    }

    public Integer updateByCondition(SysFuncApis condition, List<Long> orgIds, SysFuncApis value) {
        return this.update(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName), Json.toMap(Json.toJson(value)));
    }



    public List<SysFuncApis> findByCondition(SysFuncApis condition,List<Long> orgIds) {
        return this.findListModelByOperateSimpleAnd(mergeCondition(Json.toMap(Json.toJson(condition)),orgIds,this.fieldOrgName));
    }
// TODO: 2022/1/5 数据组 + owner_id 来实现
    public List<SysFuncApis> findByCondition(SysFuncApis condition, List<Long> orgIds, Long userId) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", null, null);
    }

    public List<SysFuncApis> page(SysFuncApis condition, List<Long> orgIds, Long userId, Integer page, Integer size) {
        return this.findListModelByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, "*", page, size);
    }

    public Long count(SysFuncApis condition, List<Long> orgIds, Long userId) {
        return this.countByOperateSimpleAndWithOrgIds(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName);
    }

    public Integer updateByCondition(SysFuncApis condition, List<Long> orgIds, Long userId, SysFuncApis value) {
        return this.updateWithOrgIdsAndUserId(Json.toMap(Json.toJson(condition)), orgIds, userId, this.fieldUserName, this.fieldOrgName, Json.toMap(Json.toJson(value)));
    }

}