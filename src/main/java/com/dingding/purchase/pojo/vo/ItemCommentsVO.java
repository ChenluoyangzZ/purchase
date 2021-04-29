package com.dingding.purchase.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemCommentsVO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private String nickname;
    private Date createdTime;
    private String userFace;
}
