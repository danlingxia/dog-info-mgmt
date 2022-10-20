package com.doggiehome.doginfomgmt.common;

public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),

//    不存在或者空
    DOG_NO_EXIST(1004, "the dog id does not exist"),
    CAGE_NO_EXIST(1005, "the cage id does not exist"),
    ADOPT_NO_EXIST(1006, "the adopt does not exist"),
    CAGE_NO_EMPTY(2001, "the cage is not empty"),
    PARAMETER_INVALID(2002, "the param is invalid"),

//    权限相关
    NO_LOGIN(3001, "You haven't logged in yet, please log in to your account"),
    NO_PERMISSION(3002, "You do not have permission to do this, contact your administrator for permission"),
    NO_MANAGER(3003, "The manager does not exist "),
    WRONG_PASSWORD(3004, "Please check for password errors"),



//    输入数据相关
    START_INDEX_NEGATIVE(4001, "start index can't be negative"),
    PAGE_SIZE_NO_POSITIVE(4002, "page size must be positive"),

//    用户相关
    MANAGER_EXIST(5001, "the administrator user name already exists");



    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
