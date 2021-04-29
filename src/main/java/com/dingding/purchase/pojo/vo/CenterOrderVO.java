package com.dingding.purchase.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CenterOrderVO {
    private String orderId;
    private Date createdTime;
    private Integer payMethod;
    private Integer realPayAmount;
    private Integer postAmount;
    private Integer orderStatus;
    private Integer isComment;
    private List<CenterSubOrderVO> subOrderItemList;
}
