package com.dingding.purchase.time;

import com.dingding.purchase.Enum.OrderStatusEnum;
import com.dingding.purchase.mapper.OrderStatusMapper;
import com.dingding.purchase.pojo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class TimeTask {

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Scheduled(cron = "0 0/15 * * * ? ")
    public void orderPayTimeOut() {
        OrderStatus orderPayStatus = new OrderStatus();
        orderPayStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> orderStatusList = orderStatusMapper.select(orderPayStatus);
        for (OrderStatus orderPay : orderStatusList) {
            long orderPayTime = orderPay.getCreatedTime().getTime();
            long newTime = new Date().getTime();
            long timestamp = newTime - orderPayTime;
            long timeDay = 900000 ;
            if (timestamp >= timeDay) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setOrderId(orderPay.getOrderId());
                orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
                orderStatus.setCloseTime(new Date());
                orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
            }
        }
    }

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public  void orderDeliveryTimeOut() {
        OrderStatus orderDeliveryStatus = new OrderStatus();
        orderDeliveryStatus.setOrderStatus(OrderStatusEnum.WAIT_DELIVER.type);
        List<OrderStatus> orderDeliveryStatusList = orderStatusMapper.select(orderDeliveryStatus);
        for (OrderStatus orderDelivery : orderDeliveryStatusList) {
            long orderPayTime = orderDelivery.getCreatedTime().getTime();
            long newTime = new Date().getTime();
            long timestamp = newTime - orderPayTime;
            long weekDay = 604800000;
            if (timestamp >= weekDay) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setOrderId(orderDelivery.getOrderId());
                orderStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
                orderStatus.setDeliverTime(new Date());
                orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
            }

        }
    }
}