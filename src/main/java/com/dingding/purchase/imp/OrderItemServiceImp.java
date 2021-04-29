package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.OrderItemsMapper;
import com.dingding.purchase.mapper.OrderStatusMapper;
import com.dingding.purchase.pojo.OrderItems;
import com.dingding.purchase.pojo.OrderStatus;
import com.dingding.purchase.service.OrderItemService;
import com.dingding.purchase.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderItemServiceImp implements OrderItemService {
    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Override
    public void deleteOrderItem(String orderId) {
        Example example = new Example(OrderItems.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        orderItemsMapper.deleteByExample(example);
    }
}