package com.dingding.purchase.service;

import com.dingding.purchase.pojo.Orders;
import com.dingding.purchase.pojo.PageGridResult;
import com.dingding.purchase.pojo.bo.OrderBO;
import com.dingding.purchase.pojo.vo.OrderStatusVO;

public interface OrderService {
    public String createOrder(OrderBO orderBO);

    public void merchantOrderId(String orderId);

    public Integer seleteOrderPrice(String orderId);

    public PageGridResult queryCenterOrderByOrderId(String orderId, String orderStatus, Integer page, Integer pageSize);

    public void updateOrderStatus(String orderId);

    public void updateOrderStatusColse(String orderId);

    public void deleteOrder(String orderId);

    public void deleteRealOrder(String orderId);

    public void updateOrder(Orders orders);//修改订单纯纯修改

    public PageGridResult queryAllItemComment(String userId, Integer page, Integer pageSize);

    public OrderStatusVO queryCountByStatus(String userId);

    public PageGridResult queryOrderStatus(String userId, Integer page, Integer pageSize);

}