package com.nes.data;

public enum ScSysExceptionStatus {
    SUCCESS("000000", "请求成功"),
    CREATED("000001", "创建成功"),
    PARAM_NOT_NULL("001000", "请求参数不允许为空"),
    PARAM_NOT_CORRECT("001001", "请求参数不正确"),
    PARAM_WRONG_CAPTCHA("001002", "验证码不正确"),
    PARAM_CAPTCHA_EXPIRED("001003", "验证码失效"),
    PARAM_INVALID("001004", "无效请求"),
    AUTH_INVALID_USER("002000", "无效用户"),
    AUTH_WRRONG_USER_OR_PW("002001", "用户名或密码错误"),
    AUTH_UNAUTHORIZED("002002", "未认证"),
    AUTH_FAIL("002003", "认证失败"),
    AUTH_TOKEN_EXPIRED("002004", "token失效"),
    AUTH_TOKEN_NOT_EXIST("002005", "token不存在"),
    AUTH_TOKEN_FAIL("002006", "token校验失败"),
    AUTH_TOKEN_INVALID("002007", "token不可使用"),
    FORBIDDEN_NO_AUTH("003000", "无访问权限"),
    FORBIDDEN("003001", "禁止访问"),
    NOT_FOUND_URL("004000", "无效的链接"),
    NOT_FOUND_PIC("004001", "找不到图片"),
    NOT_FOUND_FILE("004002", "找不到文件"),
    NOT_FOUND_RESOURCE("004003", "找不到资源"),
    NOT_FOUND_USER("004004", "找不到用户"),
    NOT_FOUND_COMPANY("004005", "找不到公司"),
    REQUEST_TIME_OUT("005000", "请求超时"),
    REGIST_CONFLICT_PHONE("006000", "手机号已被使用"),
    REGIST_CONFLICT_EMAIL("006001", "邮箱已被使用"),
    REGIST_CONFLICT_USERNAME("006002", "用户名已被使用"),
    SERVICE_FAIL_ENCRYPT("007000", "加密失败"),
    SERVICE_FAIL_DECRYPT("007001", "解密失败"),
    SERVICE_INTERNAL("007002", "内部错误"),
    SERVICE_UNAVALIABLE("007003", "服务不可用");

    private String code;
    private String message;

    ScSysExceptionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
