package com.dingding.purchase.pojo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusVO {
    private int waitPayCounts;
    private int waitDeliverCounts;
    private int waitReceiveCounts;
    private int waitCommentCounts;
}
