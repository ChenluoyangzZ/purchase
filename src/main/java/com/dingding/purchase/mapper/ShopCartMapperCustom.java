package com.dingding.purchase.mapper;

import com.dingding.purchase.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCartMapperCustom {
    public List<ShopCartVO> getShopCartList(@Param("paramsList") List specIdsList);
}
