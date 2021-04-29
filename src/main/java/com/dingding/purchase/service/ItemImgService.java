package com.dingding.purchase.service;

import com.dingding.purchase.pojo.ItemsImg;

import java.util.List;

public interface ItemImgService {
    public List<ItemsImg> queryItemsImg(String itemImgId);

    public void deleteItemImg(String id, String itemId);

    public void updateItemImg(ItemsImg itemsImg);

    public void addItemImg(ItemsImg itemsImg);
}
