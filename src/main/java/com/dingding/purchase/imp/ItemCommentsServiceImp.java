package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.ItemsCommentsMapper;
import com.dingding.purchase.pojo.ItemsComments;
import com.dingding.purchase.service.ItemCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemCommentsServiceImp implements ItemCommentService {
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Override
    public List<ItemsComments> queryItemsComments(String userId, String itemId) {
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("itemId", itemId);
        return itemsCommentsMapper.selectByExample(example);
    }

    @Override
    public void deleteItemComments(String id, String itemId) {
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("itemId", itemId);
        itemsCommentsMapper.deleteByExample(example);
    }

    @Override
    public void updateItemComments(ItemsComments itemsComments,String itemId,String userId,String id) {
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("id",id);
        itemsCommentsMapper.updateByExampleSelective(itemsComments,example);
    }

    @Override
    public void addItemComments(ItemsComments itemsComments) {
      itemsCommentsMapper.insert(itemsComments);
    }
}
