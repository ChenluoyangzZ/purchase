package com.dingding.purchase.mapper;

import org.apache.ibatis.annotations.Param;

public interface ItemsSpecMapperCustom {
    //todo 解决修改问题
    public int decreaseItemSpecStock(@Param("specId")String specId,
                                     @Param("pendingCounts")int pendingCounts);
}
