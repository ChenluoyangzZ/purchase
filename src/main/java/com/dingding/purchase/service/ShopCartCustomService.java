package com.dingding.purchase.service;

import com.dingding.purchase.pojo.vo.ShopCartVO;

import java.util.List;

public interface ShopCartCustomService {
    public List<ShopCartVO> getShopCartByItemId(String specId);
}
