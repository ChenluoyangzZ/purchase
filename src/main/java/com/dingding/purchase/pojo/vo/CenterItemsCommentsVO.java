package com.dingding.purchase.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CenterItemsCommentsVO {
    private String commentId;
    private String content;
    private Date createdTime;
    private String itemId;
    private String itemName;
    private String specName;
    private String itemImg;
}