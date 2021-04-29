package com.dingding.purchase.Enum;

public enum CommentLevelEnum {
   GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评"),
    ;

    public Integer type;
    public String value;

    CommentLevelEnum(Integer type, String value) {
        this.value = value;
        this.type = type;
    }
}
