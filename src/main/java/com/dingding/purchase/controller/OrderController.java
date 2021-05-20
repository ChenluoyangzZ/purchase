package com.dingding.purchase.controller;

import com.dingding.purchase.mapper.ItemsSpecMapper;
import com.dingding.purchase.mapper.OrderStatusMapper;
import com.dingding.purchase.pojo.ItemsSpec;
import com.dingding.purchase.pojo.Shopcart;
import com.dingding.purchase.pojo.bo.OrderBO;
import com.dingding.purchase.pojo.bo.ShopCartBO;
import com.dingding.purchase.service.OrderItemService;
import com.dingding.purchase.service.OrderService;
import com.dingding.purchase.service.OrderStatusService;
import com.dingding.purchase.service.ShopCartService;
import com.dingding.purchase.uitls.CookieUtils;
import com.dingding.purchase.uitls.RespondResultUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "订单接口")
@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ShopCartController shopCartController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private  ItemsSpecMapper itemsSpecMapper;

    @ApiOperation("创建订单")
    @PostMapping("/create")
    @SneakyThrows
    public String createOrder(@RequestBody OrderBO orderBO,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) {
        if (StringUtils.isBlank(orderBO.getUserId())) {
          return   RespondResultUtils.errorMsg("没有传入userId");
        }
        List<ShopCartBO> shopCartBOS = shopCartController.getShopCartBOS(orderBO.getUserId());
        if(shopCartBOS == null || shopCartBOS.isEmpty()){
            CookieUtils.setCookie(httpServletRequest, httpServletResponse, "shopcart", "", true);
        }else {
            String[] split = orderBO.getItemSpecIds().split(",");
            String[] split1 = orderBO.getItemAmounts().split(",");
            for (int i = 0; i < split.length; i++) {
                ItemsSpec itemsSpec = itemsSpecMapper.selectByPrimaryKey(split[i]);
                if (Integer.parseInt(split1[i])>itemsSpec.getStock()){
                    return RespondResultUtils.errorMsg("选货数量大于库存");
                }else {
                    shopCartController.updateIsDelete(orderBO.getUserId(),split[i]);
                }
            }
            List<ShopCartBO> shopCartBO = shopCartController.getShopCartBOS(orderBO.getUserId());
            CookieUtils.setCookie(httpServletRequest, httpServletResponse, "shopcart", objectMapper.writeValueAsString(shopCartBO), true);
        }
        return RespondResultUtils.ok(orderService.createOrder(orderBO));
    }

    @ApiOperation("支付成功回调")
    @SneakyThrows
    @PostMapping("/merchantOrder")
    public String addUserAddress(@RequestParam String merchantOrderId) {
        orderService.merchantOrderId(merchantOrderId);
        return RespondResultUtils.ok();
    }

    @ApiOperation("管理员-删除订单")
    @SneakyThrows
    @DeleteMapping("/del")
    public String deleteOrder(@RequestParam String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return RespondResultUtils.errorMsg("参数错误");
        }
        orderService.deleteRealOrder(orderId);
        orderStatusService.deleteOrderStatus(orderId);
        orderItemService.deleteOrderItem(orderId);
        return RespondResultUtils.ok();
    }
}