package com.dingding.purchase.pojo.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CenterSubOrderVO {
    private String itemId;
    private String itemName;
    private String itemImg;
    private String itemSpecName;
    private Integer buyCounts;
    private Integer price;
}
