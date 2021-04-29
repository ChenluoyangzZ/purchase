package com.dingding.purchase.service;

import com.dingding.purchase.pojo.ItemsImg;
import com.dingding.purchase.pojo.ItemsSpec;

public interface ItemSpecService {
    ItemsSpec query(String id);

    void delete(String id);

    void update(ItemsSpec itemsSpec);

    void add(ItemsSpec itemsSpec);
}
