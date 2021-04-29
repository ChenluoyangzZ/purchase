package com.dingding.purchase.pojo.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CenterItemCommentBO {
    private String itemId;
    private  String itemName;
    private  String itemSpecName;
    private Integer CommentLevel;
    private  String content;
}