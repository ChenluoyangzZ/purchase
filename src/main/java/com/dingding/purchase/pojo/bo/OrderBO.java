package com.dingding.purchase.pojo.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBO {
    private String userId;
    private String itemAmounts;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
