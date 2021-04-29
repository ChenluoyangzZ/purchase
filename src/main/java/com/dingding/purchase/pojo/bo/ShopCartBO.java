package com.dingding.purchase.pojo.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopCartBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private Integer priceDiscount;
    private Integer priceNormal;
}