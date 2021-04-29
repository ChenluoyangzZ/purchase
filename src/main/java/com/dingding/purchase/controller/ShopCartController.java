package com.dingding.purchase.controller;

import com.dingding.purchase.mapper.ItemsSpecMapper;
import com.dingding.purchase.pojo.ItemsSpec;
import com.dingding.purchase.pojo.Shopcart;
import com.dingding.purchase.pojo.bo.ShopCartBO;
import com.dingding.purchase.service.ShopCartService;
import com.dingding.purchase.uitls.CookieUtils;
import com.dingding.purchase.uitls.RespondResultUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "购物车接口")
@RequestMapping("/shopcart")
@RestController
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    @SneakyThrows
    public String addShopCart(@RequestParam String userId,
                              @RequestBody ShopCartBO shopCartBO,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) {
        if ( shopCartBO.getBuyCounts()==null || shopCartBO.getBuyCounts()<=0) {
            return RespondResultUtils.errorMsg("商品数量错误");
        }
        if (shopCartService.queryShopcart(shopCartBO.getItemId(), shopCartBO.getSpecId(), userId)) {
            //商品不存后创建商品的购物车
            Shopcart shopcart = new Shopcart();
            shopcart.setBuyCounts(shopCartBO.getBuyCounts());
            shopcart.setCreatedTime(new Date());
            shopcart.setId(Sid.nextShort());
            shopcart.setItemId(shopCartBO.getItemId());
            shopcart.setItemName(shopCartBO.getItemName());
            shopcart.setSpecId(shopCartBO.getSpecId());
            shopcart.setSpecName(shopCartBO.getSpecName());
            shopcart.setPriceNormal(shopCartBO.getPriceNormal());
            shopcart.setPriceDiscount(shopCartBO.getPriceDiscount());
            shopcart.setItemImgUrl(shopCartBO.getItemImgUrl());
            shopcart.setIsDelete(0);
            shopcart.setUserId(userId);
            shopCartService.addItemToShopCart(shopcart);
        } else {
            //商品存在 将数目累加 进行对商品的更新
            Shopcart query = shopCartService.query(shopCartBO.getItemId(), shopCartBO.getSpecId(), userId);
            Integer number = null;
            number = query.getBuyCounts() + shopCartBO.getBuyCounts();
            Shopcart shopcart = new Shopcart();
            shopcart.setUpdatedTime(new Date());
            shopcart.setBuyCounts(number);
            shopCartService.update(shopcart, shopCartBO.getItemId(), shopCartBO.getSpecId());
        }
        //将查询的商品封装为BO返回给前端
        List<ShopCartBO> shopCartVOList = getShopCartBOS(userId);
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "shopcart", objectMapper.writeValueAsString(shopCartVOList), true);
        return RespondResultUtils.ok();
    }

    public List<ShopCartBO> getShopCartBOS(@RequestParam String userId) {
        List<Shopcart> query = shopCartService.queryUserALLShopCart(userId);
        List<ShopCartBO> shopCartVOList = new ArrayList<>();
        for (Shopcart shopcart : query) {
            ShopCartBO shopCartVO = new ShopCartBO();
            BeanUtils.copyProperties(shopcart, shopCartVO);
            shopCartVOList.add(shopCartVO);
        }
        return shopCartVOList;
    }

    @ApiOperation("删除商品从购物车")
    @PostMapping("/del")
    @SneakyThrows
    public String delShopCart(@RequestParam String userId,
                              @RequestParam String itemSpecId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
          return   RespondResultUtils.errorMsg("参数不能为空");
        }
        updateIsDelete(userId, itemSpecId);
        List<ShopCartBO> shopCartVOList = getShopCartBOS(userId);
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "shopcart", objectMapper.writeValueAsString(shopCartVOList), true);
        return RespondResultUtils.ok();
    }

    public void updateIsDelete(@RequestParam String userId, @RequestParam String itemSpecId) {
        Shopcart shopcart = new Shopcart();
        shopcart.setId(shopCartService.query(itemsSpecMapper.selectByPrimaryKey(itemSpecId).getItemId(),itemSpecId,userId).getId());
        shopcart.setIsDelete(1);
        shopCartService.updateIsDelete(shopcart);
    }
}