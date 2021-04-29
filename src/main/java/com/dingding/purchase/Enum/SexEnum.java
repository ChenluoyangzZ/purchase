package com.dingding.purchase.Enum;

public enum SexEnum {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");


    public final String value;
    public final int type;

    SexEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}