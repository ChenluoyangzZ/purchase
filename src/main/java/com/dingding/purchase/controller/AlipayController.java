package com.dingding.purchase.controller;

import com.dingding.purchase.service.IpayService;
import com.dingding.purchase.uitls.RespondResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "支付宝支付接口")
@RestController
@RequestMapping("/payment")
public class AlipayController {

    @Autowired
    private IpayService ipayService;

    @ApiOperation("支付宝进行支付")
    @PostMapping("/goAlipay")
    public String addShopCartBySpecId(@RequestParam String merchantUserId,
                                      @RequestParam String merchantOrderId) {
        return RespondResultUtils.ok(ipayService.alipay(merchantOrderId, merchantUserId));
    }
}
