### 后端各类可视化自动化工具

#### 自动化代码审计工具

#### 自动化权限维护
表结构如下：
```sql
--RBAC之api基础表
CREATE TABLE IF NOT EXISTS sys_base_apis
(
    id            bigint PRIMARY KEY    DEFAULT random_id(), --记录标识
    method        varchar(64)  NOT NULL DEFAULT 'GET',       --HTTP方法
    url           varchar(64)  NOT NULL DEFAULT '',          --URL
    type          varchar(64)  NOT NULL DEFAULT 'page',      --api类型，page/api
    icon          varchar(64)  NOT NULL DEFAULT '',          --前端所需的icon标记
    description   varchar(500) NOT NULL DEFAULT '--',        --描述，用于选择权限时展示
    father_id     bigint       NOT NULL DEFAULT '0',         --父api的Id，主要用于给api做页面归属


    --通用管理信息
    created_id    bigint       NOT NULL DEFAULT '0',         --创建人标识
    created_name  varchar(64)  NOT NULL DEFAULT '--',        --创建人姓名
    created_time  timestamp    NOT NULL DEFAULT now(),       --创建时间
    modified_id   bigint       NULL,                         --修改人标识
    modified_name varchar(64)  NULL,                         --修改人姓名
    modified_time timestamp    NULL,                         --修改时间
    deleted_id    bigint       NULL,                         --删除人标识
    deleted_name  varchar(64)  NULL,                         --删除人姓名
    deleted_time  timestamp    NULL,                         --删除时间
    deleted_mark  boolean      NOT NULL DEFAULT 'F',         --删除标记
    UNIQUE (method, url)                                     --业务系统名称、所属单位标识构成唯一
);

--RBAC之功能表
CREATE TABLE IF NOT EXISTS sys_func_apis
(
    id            bigint PRIMARY KEY    DEFAULT random_id(), --记录标识
    name          varchar(64)  NOT NULL DEFAULT '--',        --功能名
    base_api_ids  text NOT NULL DEFAULT '0',                 --组成功能的基础api的逗分id
    description   varchar(500) NOT NULL DEFAULT '--',        --描述
    type          varchar(64)  NOT NULL DEFAULT '',          --类型 page/apis 分为页面功能和apis功能集
    father_id     bigint       NOT NULL DEFAULT '0',         --上层功能id
    rank          int8         NOT NULL DEFAULT '0',         --功能展示的顺序

    --通用管理信息
    created_id    bigint       NOT NULL DEFAULT '0',         --创建人标识
    created_name  varchar(64)  NOT NULL DEFAULT '--',        --创建人姓名
    created_time  timestamp    NOT NULL DEFAULT now(),       --创建时间
    modified_id   bigint       NULL,                         --修改人标识
    modified_name varchar(64)  NULL,                         --修改人姓名
    modified_time timestamp    NULL,                         --修改时间
    deleted_id    bigint       NULL,                         --删除人标识
    deleted_name  varchar(64)  NULL,                         --删除人姓名
    deleted_time  timestamp    NULL,                         --删除时间
    deleted_mark  boolean      NOT NULL DEFAULT 'F',         --删除标记
    UNIQUE (base_api_ids, father_id)
);
```
相对于标准的rbac，我们增加了api集合即功能的概念，但是这部分为手动维护，成本较高，所以在这里进行可视化维护。