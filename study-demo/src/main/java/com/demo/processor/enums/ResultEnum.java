package com.demo.processor.enums;

/**
 * Created by wu on 16/10/10.
 */
public enum ResultEnum {
    SUCCESS(200, "成功"),

    INNER_ERROR(500, "服务器错误"),
    ;

    private int code;

    private String msg;

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum fromCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return INNER_ERROR;
    }

    @Override
    public String toString() {
        return "ResultEnum{" + "code=" + code + ", msg=" + msg  + '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
