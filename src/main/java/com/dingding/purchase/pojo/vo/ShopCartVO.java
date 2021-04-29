package com.dingding.purchase.pojo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopCartVO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer priceDiscount;
    private Integer priceNormal;
}