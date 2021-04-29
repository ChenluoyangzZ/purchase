package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.ItemsParamMapper;
import com.dingding.purchase.pojo.ItemsParam;
import com.dingding.purchase.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ItemParamServiceImp implements ItemParamService {
    @Autowired
    private  ItemsParamMapper itemsParamMapper;
    @Override
    public ItemsParam query(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(ItemsParam itemsParam) {

    }

    @Override
    public void add(ItemsParam itemsParam) {
        itemsParamMapper.insertSelective(itemsParam);
    }
}
