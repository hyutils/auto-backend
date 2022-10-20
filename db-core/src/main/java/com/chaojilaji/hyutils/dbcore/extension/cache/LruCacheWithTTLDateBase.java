package com.chaojilaji.hyutils.dbcore.extension.cache;


import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LruCacheWithTTLDateBase {


    @Value("${lru.cache.second}")
    private Integer second;

    /**
     * 缓存键规则：查询条件为键
     * 唯一缓存标记（用于区分不同的业务场景） + 条件（条件1：值（如果有）+ 条件2：值（如果有）+ 条件3：值（如果有））
     * 缓存值规则：返回的值的Json为值
     */
    private LRUMap<String, String> cache = new LRUMap<>(20, 10000);
    private ConcurrentHashMap<String, Long> ttls = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,Integer> seconds = new ConcurrentHashMap<>();

    public String getCacheJson(String className, String methodName, Map<String, Object> condition) {
        String key = className + "+" + methodName + "+" + getSortedConditionStr(condition);
        if (ttls.containsKey(key)) {
            if (DatetimeUtil.getDateTimeOfTimestamp(ttls.get(key)).plusSeconds(second).isBefore(LocalDateTime.now())) {
                // TODO: 2021/9/29 如果当前时间没有超时，直接返回结果
                ttls.remove(key);
            } else {
                // TODO: 2021/9/29 删除缓存
                return cache.get(key);
            }
        }
        return null;
    }

    public String getCacheJsonWithTTl(String className, String methodName, Map<String, Object> condition) {
        String key = className + "+" + methodName + "+" + getSortedConditionStr(condition);
        if (ttls.containsKey(key)) {
            if (DatetimeUtil.getDateTimeOfTimestamp(ttls.get(key)).plusSeconds(seconds.get(key)).isBefore(LocalDateTime.now())) {
                // TODO: 2021/9/29 删除缓存
                ttls.remove(key);
                seconds.remove(key);
            } else {
                // TODO: 2021/9/29 如果当前时间没有超时，直接返回结果
                return cache.get(key);
            }
        }
        return null;
    }

    private String getSortedConditionStr(Map<String, Object> condition) {
        Set<String> keys = condition.keySet();
        List list = new ArrayList(keys);
        Object[] ary = list.toArray();
        Arrays.sort(ary);
        list = Arrays.asList(ary);
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i) + "=" + condition.get(list.get(i) + "") + "&";
        }
        return str;
    }

    public Boolean putCacheJson(String className, String methodName, Map<String, Object> condition, String value) {
        try {
            String key = className + "+" + methodName + "+" + getSortedConditionStr(condition);
            ttls.put(key, DatetimeUtil.getTimestampOfDatetime(LocalDateTime.now()));
            cache.put(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean putCacheJsonWithTTL(String className, String methodName, Map<String, Object> condition, String value,Integer ttl) {
        try {
            String key = className + "+" + methodName + "+" + getSortedConditionStr(condition);
            ttls.put(key, DatetimeUtil.getTimestampOfDatetime(LocalDateTime.now()));
            seconds.put(key,ttl);
            cache.put(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
