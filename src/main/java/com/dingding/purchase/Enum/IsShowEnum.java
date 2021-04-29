package com.dingding.purchase.Enum;

public enum IsShowEnum {
    YES(1, "是"),
    NO(0, "否"),
    ;

    public Integer type;
    public String value;

    IsShowEnum(Integer type, String value) {
        this.value = value;
        this.type = type;
    }
}