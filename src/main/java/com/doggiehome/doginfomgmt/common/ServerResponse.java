package com.doggiehome.doginfomgmt.common;

import lombok.ToString;

import java.util.Map;

@ToString
public class ServerResponse<T> {

    private int code;
    private String msg;
    private T data;
//    private Map dataMap;

    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    private ServerResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    private ServerResponse(int code) {
        this.code = code;
    }

    private ServerResponse(T data) {
        this.data = data;
    }

    public static <T> ServerResponse<T> errorResponse(int code, String msg){
        return new ServerResponse<T>(code, msg);
    }
    public static <T> ServerResponse<T> errorResponse(int code) {
        return new ServerResponse<T>(code);
    }

    public static <T> ServerResponse<T> errorResponse(T data) {
        return new ServerResponse<T>(data);
    }

    public static <T> ServerResponse<T> successResponse(int code, String msg, T data){
        return new ServerResponse<T>(code, msg, data);
    }

    public static <T> ServerResponse<T> successResponse(int code, String msg){
        return new ServerResponse<T>(code, msg);
    }

    public static <T> ServerResponse<T> successResponse(T data){
        return new ServerResponse<T>(data);
    }

//    public static <T> ServerResponse<T> successResponse(Map dataMap){
//        return new ServerResponse<T>(dataMap);
//    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

//    public ServerResponse(Map dataMap) {
//        this.dataMap = dataMap;
//    }
//
//    public Map getDataMap() {
//        return dataMap;
//    }


}
