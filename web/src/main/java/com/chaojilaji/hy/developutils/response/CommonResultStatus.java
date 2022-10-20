package com.chaojilaji.hy.developutils.response;

public enum CommonResultStatus implements ResultStatus {
    OK(10200, "成功"),
    FIRST_LOGIN(10111, "首次登录需要重新更改密码"),
    NOT_LOGIN(10401, "未登录"),
    AUTH_EXPIRE(11401, "认证信息过期"),
    AUTH_WRONG(12401, "认证信息错误"),
    NOT_CHANGE_DEFAULT_PASSWORD(13401, "首次登录未修改密码"),
    PAGE_NOT_AUTH(11403, "页面没有权限"),
    API_NOT_AUTH(12403, "接口(路由)没有权限"),
    FILE_NOT_AUTH(13403, "文件访问没有权限"),
    SERVER_WRONG(10500, "服务端错误"),
    UPDATE_FAIL(10404, "状态更新失败"),
    IMPORT_FAIL(10405, "导入失败"),
    USER_NOT_EXIST(10502, "用户名或密码错误"),
    USER_HAS_NO_ROLES(10511, "账号权限异常，无法登录"),
    USER_ALREADY_EXIST(10414, "当前用户已存在"),
    PLUG_NOT_EXIST(10502, "插件不存在"),
    CAPTCHA_WRONG(10503, "验证码错误"),
    PLUG_NODE_NOT_EXIST(10502, "插件节点不存在"),
    ROLE_NAME_EXIST(10510, "角色名称已存在"),
    PARAM_WRONG(10513, "参数错误"),
    PARAM_FORMAT_WRONG(10513,"参数格式错误"),
    CURRENCY_ALREADY_EXIST(10414, "已存在"),
    VERIFY_FAIL(10416, "内容校验错误"),
    PROCESS_STATUS_CHANGE_FAIL(10520, "流程流转失败"),
    FAIL(10400, "失败"),
    REPEAT_FILE(10400,"请勿重复导入"),
    IMPORT_GRADEUNIT_FILE(10400,"导入定级单元失败"),
    IMPORT_VULN_FAIL(13400, "导入漏洞失败"),
    IMPORT_ASSET_FAIL(14400, "导入资产失败"),
    IMPORT_ASSET_FAIL_IDENTIFY_ERROR(14401, "导入资产失败,识别模板错误"),
    NOT_EXISTS(10404,"目标不存在");



    private int code;
    private String message;

    CommonResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Integer findCodeByMessage(String message) {
        for (CommonResultStatus commonResultStatus : CommonResultStatus.values()) {
            if (commonResultStatus.message.equals(message)){
                return commonResultStatus.code;
            }
        }
        return 10405;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
