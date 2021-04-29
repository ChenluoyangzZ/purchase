package com.dingding.purchase.Enum;

public enum IsDefaultEnum {
    YES(1,"设置为默认"),
    NO(0,"设置为不默认")
    ;


    public final String value;
    public final int type;

    IsDefaultEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }

}
