package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.OrderStatusMapper;
import com.dingding.purchase.pojo.OrderStatus;
import com.dingding.purchase.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderStatusServiceImp implements OrderStatusService {
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Override
    public List<OrderStatus> queryAllOrderStatus() {
        return  orderStatusMapper.selectAll();
    }

    @Override
    public void deleteOrderStatus(String orderId) {
        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        orderStatusMapper.deleteByExample(example);
    }
}
