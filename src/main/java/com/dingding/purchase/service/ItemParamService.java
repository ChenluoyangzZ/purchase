package com.dingding.purchase.service;

import com.dingding.purchase.pojo.ItemsParam;

public interface ItemParamService {
    ItemsParam query(String id);

    void delete(String id);

    void update(ItemsParam itemsParam);

    void add(ItemsParam itemsParam);
}
