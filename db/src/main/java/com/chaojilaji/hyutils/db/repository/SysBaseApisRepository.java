package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.SysBaseApis;
import com.chaojilaji.hyutils.db.query.SysBaseApisQuery;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.chaojilaji.hyutils.dbcore.syntaxtree.AndWhereSyntaxTree;
import com.chaojilaji.hyutils.dbcore.syntaxtree.WhereSyntaxTree;
import org.springframework.util.StringUtils;
import com.chaojilaji.hyutils.dbcore.extension.visible.VisibleUser;

public interface SysBaseApisRepository {

    default SysBaseApis findSysBaseApisById(Long id) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findModelById(id);
    }

    default SysBaseApis findSysBaseApisByCondition(SysBaseApis condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(SysBaseApis sysBaseApis) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.insert(Json.toMap(Json.toJson(sysBaseApis)));
    }


    default Integer saveAll(List<SysBaseApis> sysBaseApiss) {
        List<Map<String, Object>> sysBaseApisMaps = new ArrayList<>();
        for (SysBaseApis sysBaseApis : sysBaseApiss) {
            sysBaseApisMaps.add(Json.toMap(Json.toJson(sysBaseApis)));
        }
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.batchInsert(sysBaseApisMaps);
    }

    default List<SysBaseApis> findAll() {
        SysBaseApisQuery query = new SysBaseApisQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<SysBaseApis> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), SysBaseApis.class));
        }
        return ans;
    }

    default Integer update(SysBaseApis condition, SysBaseApis value) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, SysBaseApis value) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(SysBaseApis sysBaseApis) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.count(sysBaseApis);
    }

    default Long count(List<Triplet<String, String, Object>> sysBaseApis) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.count(sysBaseApis);
    }

    default List<SysBaseApis> page(SysBaseApis sysBaseApis, Integer page, Integer size) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.page(sysBaseApis, page, size);
    }

    default List<SysBaseApis> page(List<Triplet<String, String, Object>> sysBaseApis, Integer page, Integer size) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.findListModelByOperateSimpleAnd(sysBaseApis, page, size);
    }

    default List<SysBaseApis> findAllByCondition(SysBaseApis condition){
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(condition);
    }
    default List<SysBaseApis> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<SysBaseApis> sysBaseApiss = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            sysBaseApiss.add(Json.toObject(Json.toJson(x),SysBaseApis.class));
        }
        return sysBaseApiss;
    }
    default SysBaseApis findSysBaseApisByIdWithCache(Long id) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String tmp = query.getFromCache("SysBaseApisRepository", "findSysBaseApisByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, SysBaseApis.class);
        } else {
            SysBaseApis sysBaseApis = query.findModelById(id);
            if (Objects.nonNull(sysBaseApis)) {
                query.putToCache("SysBaseApisRepository", "findSysBaseApisByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(sysBaseApis));
            }
            return sysBaseApis;
        }
    }

    default SysBaseApis findSysBaseApisByConditionWithCache(SysBaseApis condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        SysBaseApisQuery query = new SysBaseApisQuery();
        String tmp = query.getFromCache("SysBaseApisRepository", "findSysBaseApisByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, SysBaseApis.class);
        } else {
            SysBaseApis sysBaseApis = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(sysBaseApis)) {
                query.putToCache("sysBaseApisRepository","findSysBaseApisByConditionWithCache",andCondition,Json.toJson(sysBaseApis));
            }
            return sysBaseApis;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<SysBaseApis> findAllByCondition(SysBaseApis condition, List<Long> orgIds) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(condition, orgIds);
    }


    default SysBaseApis findSysDataAuthByCondition(SysBaseApis condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<SysBaseApis> findAll(List<Long> orgIds) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(new SysBaseApis(), orgIds);
    }

    default Integer update(SysBaseApis condition, List<Long> orgIds, SysBaseApis value) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(SysBaseApis sysBaseApis, List<Long> orgIds) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.count(sysBaseApis, orgIds);
    }

    default List<SysBaseApis> page(SysBaseApis sysBaseApis, List<Long> orgIds, Integer page, Integer size) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.page(sysBaseApis, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<SysBaseApis> findAllByCondition(SysBaseApis condition, List<Long> orgIds, Long ownerId) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }



    default SysBaseApis findSysBaseApisByCondition(SysBaseApis condition, List<Long> orgIds, Long ownerId) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        List<SysBaseApis> sysBaseApiss = query.page(condition, orgIds, ownerId, 1, 1);
        if (sysBaseApiss.size() != 0) {
            return sysBaseApiss.get(0);
        }
        return null;
    }

    default List<SysBaseApis> findAll(List<Long> orgIds, Long ownerId) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(new SysBaseApis(), orgIds, ownerId);
    }

    default Integer update(SysBaseApis condition, List<Long> orgIds, Long ownerId, SysBaseApis value) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(SysBaseApis sysBaseApis, List<Long> orgIds, Long ownerId) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.count(sysBaseApis, orgIds, ownerId);
    }

    default List<SysBaseApis> page(SysBaseApis sysBaseApis, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.page(sysBaseApis, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<SysBaseApis> page(SysBaseApis sysBaseApis, VisibleUser visibleUser, Integer page, Integer size) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.page(sysBaseApis,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(SysBaseApis sysBaseApis, VisibleUser visibleUser) {
        SysBaseApisQuery sysBaseApisQuery = new SysBaseApisQuery();
        return sysBaseApisQuery.count(sysBaseApis, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<SysBaseApis> findAll(VisibleUser visibleUser) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(new SysBaseApis(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default SysBaseApis findSysBaseApisByCondition(SysBaseApis condition, VisibleUser visibleUser) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        List<SysBaseApis> sysBaseApis = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (sysBaseApis.size() != 0) {
            return sysBaseApis.get(0);
        }
        return null;
    }

    default List<SysBaseApis> findAllByCondition(SysBaseApis condition, VisibleUser visibleUser) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default SysBaseApis findSysBaseApisByLikeCondition(SysBaseApis condition) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findModelByOperateSimpleAnd(params);
    }

    default SysBaseApis findSysBaseApisByLikeConditionAndEqualCondition(SysBaseApis likeCondition, SysBaseApis equalCondition) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String json = Json.toJson(likeCondition);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalCondition));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findModelByOperateSimpleAnd(params);
    }

    default List<SysBaseApis> pageWithLike(SysBaseApis sysBaseApis, Integer page, Integer size) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String json = Json.toJson(sysBaseApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<SysBaseApis> pageWithLikeAndEqual(SysBaseApis likeSysBaseApis, SysBaseApis equalSysBaseApis, Integer page, Integer size) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String json = Json.toJson(likeSysBaseApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalSysBaseApis));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(SysBaseApis sysBaseApis) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String json = Json.toJson(sysBaseApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(SysBaseApis likeSysBaseApis, SysBaseApis equalSysBaseApis) {
        SysBaseApisQuery query = new SysBaseApisQuery();
        String json = Json.toJson(likeSysBaseApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalSysBaseApis));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }}