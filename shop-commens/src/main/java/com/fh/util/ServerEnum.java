package com.fh.util;

public enum ServerEnum {

    ACCOUNT_OR_PWD_ISNULL(1001,"账号或者密码不存在")
    ,ACCOUNT_ERROR(5001,"账号异常，请联系管理员")
    ,ACCOUNT_NOT_EXIST(1002,"账号不存在请联系管理员")
    ,ACCOUNT_NOT_INVALID(1003,"账号无效请联系管理员")
    ,PWD_ERROR(1004,"密码输入有误，请重新输入")
    ,RIGHT_NO(1005,"该用户没有权限访问，请联系管理员")
    ,TOKEN_TIMEOUT(6001,"token失效了，请重新登录")
    ,SAFETY_ERROR(9000,"接口验签失败")
    ,SERVICE_CONNECTION_EXCEPTION(9001,"服务连接异常")
    ,SERVICE_CONNECTION_OVERTIME(9002,"服务器繁忙")
    ,SERVICE_CONNECTION_UNKNOWN(9003,"服务器连接失败")
    ,SECRET_ERROR(6001,"token验证失败，非法请求")
    ,EXPORT_NULL(7001,"导出数据是空的")
    ,SUCCESS(200,"成功")
    ,ERROR(500,"失败")
    ,PHONE_ISNULL(9004,"输入的手机号有误")
    ;
    private ServerEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
