package com.dingding.purchase.imp;

import com.dingding.purchase.Enum.IsCommentEnum;
import com.dingding.purchase.Enum.IsDefaultEnum;
import com.dingding.purchase.Enum.OrderStatusEnum;
import com.dingding.purchase.mapper.*;
import com.dingding.purchase.pojo.*;
import com.dingding.purchase.pojo.bo.OrderBO;
import com.dingding.purchase.pojo.vo.CenterItemsCommentsVO;
import com.dingding.purchase.pojo.vo.CenterOrderVO;
import com.dingding.purchase.pojo.vo.OrderStatusVO;
import com.dingding.purchase.service.ItemsService;
import com.dingding.purchase.service.OrderService;
import com.dingding.purchase.service.UserAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private CenterOrdersMapper centerOrdersMapper;
    @Autowired
    private ItemsServiceImp itemsServiceImp;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Override
    public String createOrder(OrderBO orderBO) {
        UserAddress userAddress = userAddressService.queryUserOneAddress(orderBO.getUserId(), orderBO.getAddressId());
        String id = Sid.nextShort();
        //给order表添加数据
        Orders order = new Orders();
        order.setId(id);
        order.setUserId(orderBO.getUserId());

        order.setLeftMsg(orderBO.getLeftMsg());
        order.setPayMethod(orderBO.getPayMethod());

        order.setReceiverName(userAddress.getReceiver());
        order.setReceiverAddress(userAddress.getProvince() + userAddress.getCity() + userAddress.getDistrict() + userAddress.getDetail());
        order.setReceiverMobile(userAddress.getMobile());
        String[] split = orderBO.getItemSpecIds().split(",");
        Integer realPayAmount = 0;
        Integer totalAmount = 0;
        String[] split1 = orderBO.getItemAmounts().split(",");
        for(int i=0;i<split.length;i++){
            //给order表添加数据
            ItemsSpec itemsSpec = itemsService.queryItemsSpecById(split[i]);
            realPayAmount += itemsSpec.getPriceDiscount() * Integer.parseInt(split1[i]);
            totalAmount += itemsSpec.getPriceNormal() * Integer.parseInt(split1[i]);
            //通过商品配置来查找商品信息
            Items items = itemsService.queryItemById(itemsSpec.getItemId());
            //通过商品属性来查找商品图片信息
            ItemsImg itemsImg = itemsService.queryItemMainImgById(items.getId());
            //给order_item表添加数据
            OrderItems orderItems = new OrderItems();
            orderItems.setId(Sid.nextShort());
            orderItems.setItemId(items.getId());
            orderItems.setOrderId(id);
            orderItems.setItemName(items.getItemName());
            orderItems.setBuyCounts(Integer.valueOf(split1[i]));
            orderItems.setItemImg(itemsImg.getUrl());
            orderItems.setItemSpecId(itemsSpec.getId());
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(orderItems);
            //创建了订单后对商品库存进行减少
            itemsService.updateItemsSpecById(split[i], Integer.valueOf(split1[i]));
        }
        order.setRealPayAmount(realPayAmount);
        order.setTotalAmount(totalAmount);
        order.setPostAmount(0);

        order.setIsComment(IsDefaultEnum.NO.type);
        order.setIsDelete(IsDefaultEnum.NO.type);

        order.setCreatedTime(new Date());
        order.setUpdatedTime(new Date());
        ordersMapper.insert(order);
        //给order_status添加数据
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(id);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);
        return id;
    }

    @Override
    public Integer seleteOrderPrice(String orderId) {
        return ordersMapper.selectByPrimaryKey(orderId).getRealPayAmount();
    }

    @Override
    public PageGridResult queryCenterOrderByOrderId(String orderId, String orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("orderStatus", orderStatus);
        PageHelper.startPage(page, pageSize);
        List<CenterOrderVO> centerOrderVOS = centerOrdersMapper.queryOrders(map);
        PageInfo<?> pageInfo = new PageInfo<>(centerOrderVOS);
        return itemsServiceImp.genPageGridResult(page, pageInfo.getPages(), pageInfo.getTotal(), centerOrderVOS);
    }

    @Override
    public void updateOrderStatus(String orderId) {
        Orders orders =new Orders();
        orders.setUpdatedTime(new Date());
        orders.setId(orderId);
        ordersMapper.updateByPrimaryKeySelective(orders);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        orderStatus.setDeliverTime(new Date());
        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);
        orderStatusMapper.updateByExampleSelective(orderStatus, example);
    }

    @Override
    public void updateOrderStatusColse(String orderId) {
        Orders orders =new Orders();
        orders.setUpdatedTime(new Date());
        orders.setId(orderId);
        ordersMapper.updateByPrimaryKeySelective(orders);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        orderStatus.setSuccessTime(new Date());
        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
        orderStatusMapper.updateByExampleSelective(orderStatus, example);
    }

    @Override
    public void deleteOrder(String orderId) {
        Orders orders =new Orders();
        orders.setUpdatedTime(new Date());
        orders.setIsDelete(IsDefaultEnum.YES.type);
        orders.setId(orderId);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public void updateOrder(Orders orders) {
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    //查询全部评论定义的查询
    @Override
    public PageGridResult queryAllItemComment(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CenterItemsCommentsVO> centerItemsCommentsVOS = centerOrdersMapper.queryAllItemComment(userId);
        PageInfo<?> pageInfo = new PageInfo<>(centerItemsCommentsVOS);
        return itemsServiceImp.genPageGridResult(page, pageInfo.getPages(), pageInfo.getTotal(),centerItemsCommentsVOS);
    }
    //中心首页的查询数目
    @Override
    public OrderStatusVO queryCountByStatus(String userId) {
       Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);

        map.put("orderStatus",OrderStatusEnum.WAIT_PAY.type);
        int waitPayCounts=centerOrdersMapper.queryOrderCountByOrderStatus(map);

        map.put("orderStatus",OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts=centerOrdersMapper.queryOrderCountByOrderStatus(map);

        map.put("orderStatus",OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceiveCounts=centerOrdersMapper.queryOrderCountByOrderStatus(map);

        map.put("orderStatus",OrderStatusEnum.SUCCESS.type);
        map.put("isComments", IsCommentEnum.NO.type);
        int waitCommentCounts=centerOrdersMapper.queryOrderCountByOrderStatus(map);
        OrderStatusVO orderStatusVO = new OrderStatusVO();
        orderStatusVO.setWaitPayCounts(waitPayCounts);
        orderStatusVO.setWaitDeliverCounts(waitDeliverCounts);
        orderStatusVO.setWaitReceiveCounts(waitReceiveCounts);
        orderStatusVO.setWaitCommentCounts(waitCommentCounts);
        return orderStatusVO;
    }

    @Override
    public PageGridResult queryOrderStatus(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        PageHelper.startPage(page, pageSize);
        List<OrderStatus> orderStatuses = centerOrdersMapper.queryOrderStatus(map);
        PageInfo<?> pageInfo = new PageInfo<>(orderStatuses);
        return itemsServiceImp.genPageGridResult(page, pageInfo.getPages(), pageInfo.getTotal(),orderStatuses);
    }

    @Override
    public void merchantOrderId(String orderId) {
        Orders orders = new Orders();
        orders.setUpdatedTime(new Date());
        orders.setId(orderId);
        ordersMapper.updateByPrimaryKeySelective(orders);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_DELIVER.type);
        orderStatus.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    public void deleteRealOrder(String orderId) {
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        ordersMapper.deleteByExample(example);
    }
}
