package com.dingding.purchase.service;

import com.dingding.purchase.pojo.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    public List<OrderStatus> queryAllOrderStatus();

    public void  deleteOrderStatus(String orderId);
}
