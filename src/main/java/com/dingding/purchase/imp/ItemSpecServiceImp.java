package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.ItemsSpecMapper;
import com.dingding.purchase.pojo.ItemsSpec;
import com.dingding.purchase.service.ItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSpecServiceImp implements ItemSpecService {
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Override
    public ItemsSpec query(String id) {
        return itemsSpecMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(String id) {
        itemsSpecMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ItemsSpec itemsSpec) {
     itemsSpecMapper.updateByPrimaryKeySelective(itemsSpec);
    }

    @Override
    public void add(ItemsSpec itemsSpec) {
     itemsSpecMapper.insert(itemsSpec);
    }
}
