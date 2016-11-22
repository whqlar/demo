package com.demo.design.processor.enums;

public enum BizChannelEnum {


    APP_INDEX(1, "App首页千人千面");

    private int code;

    private String desc;

    private BizChannelEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static BizChannelEnum fromCode(int code) {
        for (BizChannelEnum item : values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}