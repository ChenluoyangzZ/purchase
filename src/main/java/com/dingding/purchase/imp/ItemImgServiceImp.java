package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.ItemsCommentsMapper;
import com.dingding.purchase.mapper.ItemsImgMapper;
import com.dingding.purchase.pojo.ItemsComments;
import com.dingding.purchase.pojo.ItemsImg;
import com.dingding.purchase.service.ItemCommentService;
import com.dingding.purchase.service.ItemImgService;
import com.dingding.purchase.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemImgServiceImp implements ItemImgService {
    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Override
    public List<ItemsImg> queryItemsImg(String itemImgId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemImgId);
        return itemsImgMapper.selectByExample(example);
    }


    @Override
    public void deleteItemImg(String id, String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("itemId", itemId);
        itemsImgMapper.deleteByExample(example);
    }

    @Override
    public void updateItemImg(ItemsImg itemsImg) {
           itemsImgMapper.updateByPrimaryKeySelective(itemsImg);
    }

    @Override
    public void addItemImg(ItemsImg itemsImg) {
      itemsImgMapper.insert(itemsImg);
    }
}
