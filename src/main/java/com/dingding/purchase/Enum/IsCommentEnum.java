package com.dingding.purchase.Enum;

public enum IsCommentEnum {
    YES(1,"设置评论"),
    NO(0,"设置没有评论")
    ;


    public final String value;
    public final int type;

    IsCommentEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }

}
