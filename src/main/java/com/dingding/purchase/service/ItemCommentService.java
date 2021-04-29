package com.dingding.purchase.service;

import com.dingding.purchase.pojo.ItemsComments;
import com.dingding.purchase.pojo.PageGridResult;
import com.dingding.purchase.pojo.bo.OrderBO;

import java.util.List;

public interface ItemCommentService {
   public List<ItemsComments> queryItemsComments(String userId,String itemId);

   public void deleteItemComments(String userId,String itemId);

   public void updateItemComments(ItemsComments itemsComments,String itemId,String userId,String id);

   public void addItemComments(ItemsComments itemsComments);
}
