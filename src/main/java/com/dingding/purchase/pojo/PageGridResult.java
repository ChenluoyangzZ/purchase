package com.dingding.purchase.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageGridResult {
    private int page;  //当前页数
    private int total;  //总页数
    private  long records; //总纪录行数
    private List<?> rows; //每条显示内容
}
