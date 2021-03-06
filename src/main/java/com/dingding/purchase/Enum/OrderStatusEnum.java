package com.dingding.purchase.Enum;

public enum OrderStatusEnum {
    WAIT_PAY(10, "待付款"),
    WAIT_DELIVER(20, "已付款，待发货"),
    WAIT_RECEIVE(30, "已付款，待收货"),
    SUCCESS(40, "交易成功"),
    CLOSE(50, "交易关闭");


    public final String value;
    public final int type;

    OrderStatusEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
