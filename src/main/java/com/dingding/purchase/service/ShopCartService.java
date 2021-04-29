package com.dingding.purchase.service;

import com.dingding.purchase.pojo.Shopcart;

import java.util.List;

public interface ShopCartService {
    //查询是否为商品存在
    public boolean queryShopcart(String itemId,String specId,String userId);

    public void addItemToShopCart(Shopcart shopcart);

    public void  update(Shopcart shopcart,String itemId,String specId);
//  查询用户的所有购物车
    public List<Shopcart> queryUserALLShopCart(String userId);

    public Shopcart query(String itemId,String specId,String userId);

    public void  updateIsDelete(Shopcart shopcart);
}
