package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.SysFuncApis;
import com.chaojilaji.hyutils.db.query.SysFuncApisQuery;
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

public interface SysFuncApisRepository {

    default SysFuncApis findSysFuncApisById(Long id) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findModelById(id);
    }

    default SysFuncApis findSysFuncApisByCondition(SysFuncApis condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findModelBySimpleAnd(andCondition);
    }

    default Long save(SysFuncApis sysFuncApis) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.insert(Json.toMap(Json.toJson(sysFuncApis)));
    }


    default Integer saveAll(List<SysFuncApis> sysFuncApiss) {
        List<Map<String, Object>> sysFuncApisMaps = new ArrayList<>();
        for (SysFuncApis sysFuncApis : sysFuncApiss) {
            sysFuncApisMaps.add(Json.toMap(Json.toJson(sysFuncApis)));
        }
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.batchInsert(sysFuncApisMaps);
    }

    default List<SysFuncApis> findAll() {
        SysFuncApisQuery query = new SysFuncApisQuery();
        List<Map<String, Object>> tmp = query.findAll();
        List<SysFuncApis> ans = new ArrayList<>();
        for (Map<String, Object> x : tmp) {
            ans.add(Json.toObject(Json.toJson(x), SysFuncApis.class));
        }
        return ans;
    }

    default Integer update(SysFuncApis condition, SysFuncApis value) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.updateByCondition(condition, value);
    }
    default Integer updateById(Long id, SysFuncApis value) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.updateById(id, Json.toMap(Json.toJson(value)));
    }

    default Long count(SysFuncApis sysFuncApis) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.count(sysFuncApis);
    }

    default Long count(List<Triplet<String, String, Object>> sysFuncApis) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.count(sysFuncApis);
    }

    default List<SysFuncApis> page(SysFuncApis sysFuncApis, Integer page, Integer size) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.page(sysFuncApis, page, size);
    }

    default List<SysFuncApis> page(List<Triplet<String, String, Object>> sysFuncApis, Integer page, Integer size) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.findListModelByOperateSimpleAnd(sysFuncApis, page, size);
    }

    default List<SysFuncApis> findAllByCondition(SysFuncApis condition){
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(condition);
    }
    default List<SysFuncApis> findAllWithIn(Map<String, List<Triplet<String, String, Object>>> inMaps) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        Map<String, Object> andCondition = new HashMap<>();
        andCondition.put("tag", 0);
        WhereSyntaxTree whereSyntaxTree = new WhereSyntaxTree();
        for (Map.Entry<String, List<Triplet<String, String, Object>>> map : inMaps.entrySet()) {
            List<Triplet<String, String, Object>> orCondition = map.getValue();
            andCondition.put(map.getKey(), whereSyntaxTree.createOrTreeByOperate(orCondition));
        }
        AndWhereSyntaxTree andWhereSyntaxTree = whereSyntaxTree.createAndTree(andCondition);
        List<Map<String, Object>> ans = query.find("*").where(andWhereSyntaxTree).listMapGet();
        List<SysFuncApis> sysFuncApiss = new ArrayList<>();
        for (Map<String, Object> x : ans) {
            sysFuncApiss.add(Json.toObject(Json.toJson(x),SysFuncApis.class));
        }
        return sysFuncApiss;
    }
    default SysFuncApis findSysFuncApisByIdWithCache(Long id) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        String tmp = query.getFromCache("SysFuncApisRepository", "findSysFuncApisByIdWithCache", new HashMap<String, Object>() {
            {
                put("id", id);
            }
        });
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, SysFuncApis.class);
        } else {
            SysFuncApis sysFuncApis = query.findModelById(id);
            if (Objects.nonNull(sysFuncApis)) {
                query.putToCache("SysFuncApisRepository", "findSysFuncApisByIdWithCache", new HashMap<String, Object>() {
                    {
                        put("id", id);
                    }
                }, Json.toJson(sysFuncApis));
            }
            return sysFuncApis;
        }
    }

    default SysFuncApis findSysFuncApisByConditionWithCache(SysFuncApis condition) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        SysFuncApisQuery query = new SysFuncApisQuery();
        String tmp = query.getFromCache("SysFuncApisRepository", "findSysFuncApisByConditionWithCache", andCondition);
        if (StringUtils.hasText(tmp)) {
            return Json.toObject(tmp, SysFuncApis.class);
        } else {
            SysFuncApis sysFuncApis = query.findModelBySimpleAnd(andCondition);
            if (Objects.nonNull(sysFuncApis)) {
                query.putToCache("sysFuncApisRepository","findSysFuncApisByConditionWithCache",andCondition,Json.toJson(sysFuncApis));
            }
            return sysFuncApis;
        }
    }
// TODO: 2021/12/29 数据组相关
    default List<SysFuncApis> findAllByCondition(SysFuncApis condition, List<Long> orgIds) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(condition, orgIds);
    }


    default SysFuncApis findSysDataAuthByCondition(SysFuncApis condition, List<Long> orgIds) {
        String json = Json.toJson(condition);
        Map<String, Object> andCondition = Json.toMap(json);
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findModelByOperateSimpleAnd(query.mergeCondition(andCondition, orgIds, query.fieldOrgName));
    }

    default List<SysFuncApis> findAll(List<Long> orgIds) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(new SysFuncApis(), orgIds);
    }

    default Integer update(SysFuncApis condition, List<Long> orgIds, SysFuncApis value) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.updateByCondition(condition, orgIds, value);
    }

    default Long count(SysFuncApis sysFuncApis, List<Long> orgIds) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.count(sysFuncApis, orgIds);
    }

    default List<SysFuncApis> page(SysFuncApis sysFuncApis, List<Long> orgIds, Integer page, Integer size) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.page(sysFuncApis, orgIds, page, size);
    }
    // TODO: 2022/1/6 数据组 + owner_id作为条件 
    default List<SysFuncApis> findAllByCondition(SysFuncApis condition, List<Long> orgIds, Long ownerId) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(condition, orgIds, ownerId);
    }



    default SysFuncApis findSysFuncApisByCondition(SysFuncApis condition, List<Long> orgIds, Long ownerId) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        List<SysFuncApis> sysFuncApiss = query.page(condition, orgIds, ownerId, 1, 1);
        if (sysFuncApiss.size() != 0) {
            return sysFuncApiss.get(0);
        }
        return null;
    }

    default List<SysFuncApis> findAll(List<Long> orgIds, Long ownerId) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(new SysFuncApis(), orgIds, ownerId);
    }

    default Integer update(SysFuncApis condition, List<Long> orgIds, Long ownerId, SysFuncApis value) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.updateByCondition(condition, orgIds, ownerId, value);
    }

    default Long count(SysFuncApis sysFuncApis, List<Long> orgIds, Long ownerId) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.count(sysFuncApis, orgIds, ownerId);
    }

    default List<SysFuncApis> page(SysFuncApis sysFuncApis, List<Long> orgIds, Long ownerId, Integer page, Integer size) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.page(sysFuncApis, orgIds, ownerId, page, size);
    }

// todo -- 可见范围相关，与数据组类似
    default List<SysFuncApis> page(SysFuncApis sysFuncApis, VisibleUser visibleUser, Integer page, Integer size) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.page(sysFuncApis,visibleUser.getOrgIds(),visibleUser.getId(),page,size);
    }

    default Long count(SysFuncApis sysFuncApis, VisibleUser visibleUser) {
        SysFuncApisQuery sysFuncApisQuery = new SysFuncApisQuery();
        return sysFuncApisQuery.count(sysFuncApis, visibleUser.getOrgIds(),visibleUser.getId());
    }

    default List<SysFuncApis> findAll(VisibleUser visibleUser) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(new SysFuncApis(), visibleUser.getOrgIds(), visibleUser.getId());
    }

    default SysFuncApis findSysFuncApisByCondition(SysFuncApis condition, VisibleUser visibleUser) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        List<SysFuncApis> sysFuncApis = query.page(condition, visibleUser.getOrgIds(),visibleUser.getId(), 1, 1);
        if (sysFuncApis.size() != 0) {
            return sysFuncApis.get(0);
        }
        return null;
    }

    default List<SysFuncApis> findAllByCondition(SysFuncApis condition, VisibleUser visibleUser) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        return query.findByCondition(condition, visibleUser.getOrgIds(),visibleUser.getId());
    }
// like 相关 
default SysFuncApis findSysFuncApisByLikeCondition(SysFuncApis condition) {
        SysFuncApisQuery query = new SysFuncApisQuery();
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

    default SysFuncApis findSysFuncApisByLikeConditionAndEqualCondition(SysFuncApis likeCondition, SysFuncApis equalCondition) {
        SysFuncApisQuery query = new SysFuncApisQuery();
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

    default List<SysFuncApis> pageWithLike(SysFuncApis sysFuncApis, Integer page, Integer size) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        String json = Json.toJson(sysFuncApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default List<SysFuncApis> pageWithLikeAndEqual(SysFuncApis likeSysFuncApis, SysFuncApis equalSysFuncApis, Integer page, Integer size) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        String json = Json.toJson(likeSysFuncApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalSysFuncApis));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.findListModelByOperateSimpleAnd(params, page, size);
    }

    default Long countWithLike(SysFuncApis sysFuncApis) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        String json = Json.toJson(sysFuncApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        return query.count(params);
    }

    default Long countWithLikeAndEqual(SysFuncApis likeSysFuncApis, SysFuncApis equalSysFuncApis) {
        SysFuncApisQuery query = new SysFuncApisQuery();
        String json = Json.toJson(likeSysFuncApis);
        Map<String, Object> andCondition = Json.toMap(json);
        List<Triplet<String, String, Object>> params = new ArrayList<>();
        for (Map.Entry<String, Object> x : andCondition.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "like", "%" + x.getValue() + "%"));
            }
        }
        Map<String, Object> andCondition2 = Json.toMap(Json.toJson(equalSysFuncApis));
        for (Map.Entry<String, Object> x : andCondition2.entrySet()) {
            if (Objects.nonNull(x.getValue())) {
                params.add(new Triplet<>(x.getKey(), "=", x.getValue()));
            }
        }
        return query.count(params);
    }}