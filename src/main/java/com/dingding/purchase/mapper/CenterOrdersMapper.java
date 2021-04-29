package com.dingding.purchase.mapper;

import com.dingding.purchase.pojo.OrderStatus;
import com.dingding.purchase.pojo.vo.CenterItemsCommentsVO;
import com.dingding.purchase.pojo.vo.CenterOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CenterOrdersMapper {
    public List<CenterOrderVO> queryOrders(@Param("paramMap")Map<String,Object> map);

    public List<CenterItemsCommentsVO> queryAllItemComment(String userId);

    public int queryOrderCountByOrderStatus(@Param("paramMap")Map<String,Object> map);

    public List<OrderStatus> queryOrderStatus(@Param("paramMap")Map<String,Object> map);
}