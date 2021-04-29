package com.dingding.purchase.pojo.vo;

import com.dingding.purchase.pojo.Items;
import com.dingding.purchase.pojo.ItemsImg;
import com.dingding.purchase.pojo.ItemsParam;
import com.dingding.purchase.pojo.ItemsSpec;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
