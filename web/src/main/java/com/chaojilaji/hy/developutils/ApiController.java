package com.chaojilaji.hy.developutils;

import com.chaojilaji.hy.developutils.query.*;
import com.chaojilaji.hy.developutils.response.CommonResultStatus;
import com.chaojilaji.hy.developutils.response.Result;
import com.chaojilaji.hy.developutils.response.ResultV2;
import com.chaojilaji.hy.developutils.vo.*;
import com.chaojilaji.hyutils.db.model.SysBaseApis;
import com.chaojilaji.hyutils.db.model.SysFuncApis;
import com.chaojilaji.hyutils.db.repository.SysBaseApisRepository;
import com.chaojilaji.hyutils.db.repository.SysFuncApisRepository;
import com.chaojilaji.hyutils.dbcore.utils.ArrayStrUtil;
import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    @Autowired
    private SysFuncApisRepository sysFuncApisRepository;

    @Autowired
    private SysBaseApisRepository sysBaseApisRepository;

    private static final Log logger = LogFactory.getLog(ApiController.class);


    /**
     * 获取功能列表
     *
     * @param page
     * @param size
     * @param keyword
     * @return
     */
    @GetMapping("/api/v2/funcapis")
    public ResultV2 getFuncApis(@RequestParam Integer page,
                                @RequestParam Integer size,
                                @RequestParam @Nullable String keyword, HttpServletRequest request) {
        logger.info("1 "+HttpUtils.getIpAddr(request));
        SysFuncApis.Builder builder = SysFuncApis.builder();
        if (StringUtils.hasText(keyword)) {
            builder
//                    .nameLike(keyword)
                    .descriptionLike(keyword);
        }
        return ResultV2.success(sysFuncApisRepository.page(builder.build(), page, size).stream().map(api -> {
            String baseApis = sysBaseApisRepository.findAllByCondition(SysBaseApis.builder()
                    .idList(ArrayStrUtil.str2LArray(api.getBaseApiIds()))
                    .build())
                    .stream()
                    .map(SysBaseApis::getDescription)
                    .distinct()
                    .collect(Collectors.joining(","));
            SysFuncApis father = sysFuncApisRepository.findSysFuncApisById(api.getFatherId());
            String fatherName = "--";
            String fatherDescription = "--";
            if (Objects.nonNull(father)) {
                fatherName = father.getName();
                fatherDescription = father.getDescription();
            }
            return FuncApiVo.builder()
                    .baseApiNames(baseApis)
                    .id(api.getId().toString())
                    .name(api.getName())
                    .fatherName(fatherName)
                    .description(api.getDescription())
                    .createdTime(DatetimeUtil.getViewStrOfDatetime(api.getCreatedTime()))
                    .type(api.getType().toUpperCase())
                    .build();
        }).collect(Collectors.toList()), sysFuncApisRepository.count(builder.build()).intValue());
    }

    /**
     * 获取功能API详情
     *
     * @param id
     * @return
     */
    @GetMapping("/api/v2/funcapi/{id}")
    public Result funcApiDetails(@PathVariable Long id, HttpServletRequest request) {
        logger.info("2 "+HttpUtils.getIpAddr(request));
        SysFuncApis api = sysFuncApisRepository.findSysFuncApisById(id);
        String baseApis = sysBaseApisRepository.findAllByCondition(SysBaseApis.builder()
                .idList(ArrayStrUtil.str2LArray(api.getBaseApiIds()))
                .build())
                .stream()
                .map(SysBaseApis::getDescription)
                .distinct()
                .collect(Collectors.joining(","));
        SysFuncApis father = sysFuncApisRepository.findSysFuncApisById(api.getFatherId());
        String fatherName = "--";
        String fatherDescription = "--";
        if (Objects.nonNull(father)) {
            fatherName = father.getName();
            fatherDescription = father.getDescription();
        }
        return Result.success(FuncApiDetailVo.builder()
                .baseApiNames(baseApis)
                .id(api.getId().toString())
                .name(api.getName())
                .fatherName(fatherName)
                .fatherDescription(fatherDescription)
                .description(api.getDescription())
                .createdTime(DatetimeUtil.getViewStrOfDatetime(api.getCreatedTime()))
                .type(api.getType().toUpperCase())
                .build());

    }

    /**
     * 编辑功能基本信息
     *
     * @param id
     * @param api
     * @return
     */
    @PostMapping("/api/v2/funcapi/{id}")
    public Result editFuncApiDetail(@PathVariable Long id, @RequestBody EditFuncApi api, HttpServletRequest request) {
        logger.info("3 "+HttpUtils.getIpAddr(request));
        SysFuncApis.Builder builder = SysFuncApis.builder();
        if (StringUtils.hasText(api.getDescription())) {
            builder.description(api.getDescription());
        }
        if (StringUtils.hasText(api.getName())) {
            builder.name(api.getName());
        }
        if (StringUtils.hasText(api.getType())) {
            builder.type(api.getType());
        }

        sysFuncApisRepository.updateById(id, builder.build());
        if (StringUtils.hasText(api.getFatherDescription()) || StringUtils.hasText(api.getFatherName())) {
            SysFuncApis sysFuncApis = sysFuncApisRepository.findSysFuncApisById(id);
            SysFuncApis.Builder builder1 = SysFuncApis.builder();
            if (Objects.nonNull(sysFuncApis)) {
                Long fatherId = sysFuncApis.getFatherId();
                if (StringUtils.hasText(api.getFatherName())) {
                    builder1.name(api.getFatherName());
                }
                if (StringUtils.hasText(api.getFatherDescription())) {
                    builder1.description(api.getFatherDescription());
                }
                sysFuncApisRepository.updateById(fatherId, builder1.build());
            }

        }
        return Result.success(CommonResultStatus.OK);
    }


    /**
     * 查看功能API对应的基础API列表
     *
     * @param id
     * @return
     */
    @GetMapping("/api/v2/funcapi2baseapis/{id}")
    public ResultV2 funcApi2BaseApis(@PathVariable Long id,
                                     @RequestParam("has_relation") Boolean hasRelation, HttpServletRequest request) {
        logger.info("4 "+HttpUtils.getIpAddr(request));
        SysFuncApis api = sysFuncApisRepository.findSysFuncApisById(id);
        List<Long> ids = ArrayStrUtil.str2LArray(api.getBaseApiIds());
        if (!hasRelation) {
            List<Long> tmp = sysBaseApisRepository.findAll().stream().map(SysBaseApis::getId).distinct().collect(Collectors.toList());
            tmp.removeAll(ids);
            ids.clear();
            ids.addAll(tmp);
        }
        return ResultV2.success(sysBaseApisRepository.findAllByCondition(SysBaseApis.builder()
                .idList(ids)
                .build()).stream().map(baseApis -> {
            Long fatherId = baseApis.getFatherId();
            SysBaseApis baseApis1 = sysBaseApisRepository.findSysBaseApisById(fatherId);
            String fatherName = "--";
            if (Objects.nonNull(baseApis1)) {
                fatherName = baseApis.getDescription();
            }
            return BaseApiVo.builder()
                    .createdTime(DatetimeUtil.getViewStrOfDatetime(baseApis.getCreatedTime()))
                    .description(baseApis.getDescription())
                    .fatherName(fatherName)
                    .id(baseApis.getId().toString())
                    .method(baseApis.getMethod())
                    .type(baseApis.getType().toUpperCase())
                    .url(baseApis.getUrl())
                    .build();
        }).collect(Collectors.toList()), sysBaseApisRepository.count(SysBaseApis.builder()
                .idList(ids)
                .build()).intValue());
    }

    /**
     * 处理功能API的关联关系
     */
    @PostMapping("/api/v2/funcapi2baseapi")
    public Result handleFuncApi2BaseApi(@RequestBody HandleRelationQuery query, HttpServletRequest request) {
        logger.info("5 "+HttpUtils.getIpAddr(request));
        if (StringUtils.hasText(query.getFuncId()) && StringUtils.hasText(query.getBaseId())) {
            SysFuncApis api = sysFuncApisRepository.findSysFuncApisById(Long.parseLong(query.getFuncId()));
            if (Objects.nonNull(api)) {
                List<Long> ids = ArrayStrUtil.str2LArray(api.getBaseApiIds());
                if (query.getRelationOperate()) {
                    // TODO: 2022/10/19 建立联系
                    if (ids.contains(Long.parseLong(query.getBaseId()))) {
                        return Result.success(CommonResultStatus.OK);
                    }
                    ids.add(Long.parseLong(query.getBaseId()));
                } else {
                    ids.remove(Long.parseLong(query.getBaseId()));
                }
                sysFuncApisRepository.updateById(api.getId(), SysFuncApis.builder()
                        .baseApiIds(ArrayStrUtil.llist2Str(ids, ","))
                        .build());
                return Result.success(CommonResultStatus.OK);
            }
        }
        return Result.failure(CommonResultStatus.PARAM_WRONG);
    }

    /**
     * 新建功能API
     */
    @PostMapping("/api/v2/funcapi")
    public Result addFuncApi(@RequestBody AddFuncApi addFuncApi, HttpServletRequest request) {
        logger.info("6 "+HttpUtils.getIpAddr(request));
        String fatherName = addFuncApi.getFatherName();
        SysFuncApis father = sysFuncApisRepository.findSysFuncApisByCondition(SysFuncApis.builder()
                .name(fatherName)
                .build());
        if (Objects.isNull(father)) {
            return Result.failure("父节点不存在");
        }
        sysFuncApisRepository.save(SysFuncApis.builder()
                .name(addFuncApi.getName())
                .description(addFuncApi.getDescription())
                .type(addFuncApi.getType().toUpperCase())
                .fatherId(father.getId())
                .build());
        return Result.success(CommonResultStatus.OK);
    }


    /**
     * 删除功能API
     */
    @DeleteMapping("/api/v2/funcapi/{id}")
    public Result deleteFuncApi(@PathVariable Long id, HttpServletRequest request) {
        logger.info("7 "+HttpUtils.getIpAddr(request));
        sysFuncApisRepository.updateById(id, SysFuncApis.builder()
                .deletedMark(true)
                .build());
        return Result.success(CommonResultStatus.OK);
    }


    /**
     * 基础API管理 - 列表页
     */
    @GetMapping("/api/v2/baseapis")
    public ResultV2 getPageBaseApis(@RequestParam Integer page,
                                    @RequestParam Integer size,
                                    @RequestParam @Nullable String keyword, HttpServletRequest request) {
        logger.info("8 "+HttpUtils.getIpAddr(request));
        SysBaseApis.Builder builder = SysBaseApis.builder();
        if (StringUtils.hasText(keyword)) {
            builder.descriptionLike(keyword);
//            builder.methodLike(keyword);
//            builder.urlLike(keyword);
        }

        return ResultV2.success(sysBaseApisRepository.page(builder.build(), page, size)
                        .stream().map(baseApis -> {
                            String belongs = sysFuncApisRepository.findAllByCondition(SysFuncApis.builder()
                                    .baseApiIdsLike(baseApis.getId().toString())
                                    .build()).stream().map(SysFuncApis::getName).collect(Collectors.joining(","));
                            SysBaseApis sysBaseApis = sysBaseApisRepository.findSysBaseApisById(baseApis.getFatherId());
                            String fatherName = "--";
                            if (Objects.nonNull(sysBaseApis)) {
                                fatherName = sysBaseApis.getDescription();
                            }
                            return BaseApiListVo.builder()
                                    .id(baseApis.getId().toString())
                                    .belongFuncApis(belongs)
                                    .createdTime(DatetimeUtil.getViewStrOfDatetime(baseApis.getCreatedTime()))
                                    .description(baseApis.getDescription())
                                    .fatherName(fatherName)
                                    .method(baseApis.getMethod())
                                    .type(baseApis.getType().toUpperCase())
                                    .url(baseApis.getUrl())
                                    .build();
                        }).collect(Collectors.toList())
                , sysBaseApisRepository.count(builder.build()).intValue());
    }


    /**
     * 查看基础API详情基础信息
     */
    @GetMapping("/api/v2/baseapi/{id}")
    public Result getBaseApiDetails(@PathVariable Long id, HttpServletRequest request) {
        logger.info("9 "+HttpUtils.getIpAddr(request));
        SysBaseApis api = sysBaseApisRepository.findSysBaseApisById(id);
        String fatherDescription = "--";
        String fatherPath = "--";
        SysBaseApis father = sysBaseApisRepository.findSysBaseApisById(api.getFatherId());
        if (Objects.nonNull(father)) {
            fatherDescription = father.getDescription();
            fatherPath = father.getUrl();
        }
        return Result.success(BaseApiDetailVo.builder()
                .id(api.getId().toString())
                .createdTime(DatetimeUtil.getViewStrOfDatetime(api.getCreatedTime()))
                .description(api.getDescription())
                .fatherName(fatherDescription)
                .fatherPath(fatherPath)
                .method(api.getMethod())
                .type(api.getType().toUpperCase())
                .url(api.getUrl())
                .build());
    }

    /**
     * 编辑基础功能
     */
    @PostMapping("/api/v2/baseapi/{id}")
    public Result editBaseApiBaseMessage(@PathVariable Long id, @RequestBody EditBaseAPi editBaseAPi, HttpServletRequest request) {
        logger.info("10 "+HttpUtils.getIpAddr(request));
        SysBaseApis.Builder builder = SysBaseApis.builder();
        if (StringUtils.hasText(editBaseAPi.getDescription())) {
            builder.description(editBaseAPi.getDescription());
        }
        if (StringUtils.hasText(editBaseAPi.getMethod())) {
            builder.method(editBaseAPi.getMethod());
        }
        if (StringUtils.hasText(editBaseAPi.getPath())) {
            builder.url(editBaseAPi.getPath());
        }
        if (StringUtils.hasText(editBaseAPi.getType())) {
            builder.type(editBaseAPi.getType());
        }
        sysBaseApisRepository.updateById(id, builder.build());
        if (StringUtils.hasText(editBaseAPi.getFatherDescription()) || StringUtils.hasText(editBaseAPi.getFatherPath())) {
            SysBaseApis.Builder builder1 = SysBaseApis.builder();
            SysBaseApis sysBaseApis = sysBaseApisRepository.findSysBaseApisById(id);
            SysBaseApis father = sysBaseApisRepository.findSysBaseApisById(sysBaseApis.getFatherId());
            if (Objects.nonNull(father)) {
                if (StringUtils.hasText(editBaseAPi.getFatherPath())) {
                    builder1.url(editBaseAPi.getFatherPath());
                }
                if (StringUtils.hasText(editBaseAPi.getFatherDescription())) {
                    builder1.description(editBaseAPi.getDescription());
                }
                sysBaseApisRepository.updateById(father.getId(), builder1.build());
            }
        }
        return Result.success(CommonResultStatus.OK);
    }

    /**
     * 查看基础API对应的功能api列表
     */
    @GetMapping("/api/v2/baseapi2funcapis/{id}")
    public ResultV2 getBaseApi2FuncApis(@PathVariable Long id, @RequestParam("has_relation") Boolean hasRelation, HttpServletRequest request) {
        logger.info("11 "+HttpUtils.getIpAddr(request));
        if (hasRelation) {
            // TODO: 2022/10/19 有关系
            return ResultV2.success(sysFuncApisRepository.findAllByCondition(SysFuncApis.builder()
                    .baseApiIdsLike(id.toString())
                    .build()).stream().map(sysFuncApis -> {
                SysFuncApis father = sysFuncApisRepository.findSysFuncApisById(sysFuncApis.getFatherId());
                String fatherName = "--";
                if (Objects.nonNull(father)) {
                    fatherName = father.getName();
                }
                return FuncApiVo.builder()
                        .createdTime(DatetimeUtil.getViewStrOfDatetime(sysFuncApis.getCreatedTime()))
                        .description(sysFuncApis.getDescription())
                        .fatherName(fatherName)
                        .name(sysFuncApis.getName())
                        .id(sysFuncApis.getId().toString())
                        .type(sysFuncApis.getType().toUpperCase())
                        .build();
            })
                    .collect(Collectors.toList()), sysFuncApisRepository.count(SysFuncApis.builder()
                    .baseApiIdsLike(id.toString())
                    .build()).intValue());
        } else {
            // TODO: 2022/10/19 无关系
            List<Long> ids = sysFuncApisRepository.findAllByCondition(SysFuncApis.builder()
                    .baseApiIdsLike(id.toString())
                    .build()).stream().map(SysFuncApis::getId).collect(Collectors.toList());
            List<Long> total = sysFuncApisRepository.findAllByCondition(SysFuncApis.builder()
                    .build()).stream().map(SysFuncApis::getId).collect(Collectors.toList());
            total.removeAll(ids);
            return ResultV2.success(sysFuncApisRepository.findAllByCondition(SysFuncApis.builder()
                    .idList(total)
                    .build()).stream().map(sysFuncApis -> {
                        SysFuncApis father = sysFuncApisRepository.findSysFuncApisById(sysFuncApis.getFatherId());
                        String fatherName = "--";
                        if (Objects.nonNull(father)) {
                            fatherName = father.getName();
                        }
                        return FuncApiVo.builder()
                                .createdTime(DatetimeUtil.getViewStrOfDatetime(sysFuncApis.getCreatedTime()))
                                .description(sysFuncApis.getDescription())
                                .fatherName(fatherName)
                                .name(sysFuncApis.getName())
                                .id(sysFuncApis.getId().toString())
                                .type(sysFuncApis.getType().toUpperCase())
                                .build();
                    })
                    .collect(Collectors.toList()), sysFuncApisRepository.count(SysFuncApis.builder()
                    .idList(total)
                    .build()).intValue());
        }
    }

    /**
     * 新建基础API
     */
    @PostMapping("/api/v2/baseapi")
    public Result addNewBaseApi(@RequestBody AddBaseApi addBaseApi, HttpServletRequest request){
        logger.info("12 "+HttpUtils.getIpAddr(request));
        if (StringUtils.hasText(addBaseApi.getFatherName())){
            SysBaseApis sysBaseApis = sysBaseApisRepository.findSysBaseApisByCondition(SysBaseApis.builder()
                    .description(addBaseApi.getFatherName())
                    .build());
            if (Objects.isNull(sysBaseApis)){
                return Result.failure("父级不存在");
            }
            sysBaseApisRepository.save(SysBaseApis.builder()
                    .description(addBaseApi.getDescription())
                    .url(addBaseApi.getPath())
                    .type(addBaseApi.getType().toUpperCase())
                    .method(addBaseApi.getMethod())
                    .fatherId(sysBaseApis.getId())
                    .build());
            return Result.success(CommonResultStatus.OK);
        }
        return Result.failure(CommonResultStatus.PARAM_WRONG);
    }

    /**
     * 删除基础API
     */
    @DeleteMapping("/api/v2/baseapi/{id}")
    public Result deleteBaseApi(@PathVariable Long id, HttpServletRequest request){
        logger.info("13 "+HttpUtils.getIpAddr(request));
        sysBaseApisRepository.updateById(id, SysBaseApis.builder()
                .deletedMark(true)
                .build());
        return Result.success(CommonResultStatus.OK);
    }
}
