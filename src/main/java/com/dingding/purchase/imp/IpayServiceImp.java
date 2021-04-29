package com.dingding.purchase.imp;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.dingding.purchase.config.AlipayConfig;
import com.dingding.purchase.service.IpayService;
import com.dingding.purchase.service.OrderService;
import com.dingding.purchase.uitls.TimeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class IpayServiceImp implements IpayService {

    @Autowired
    private OrderService orderService;

    @Override
    public String alipay(String merchantOrderId, String merchantUserId) {
        DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setReturnUrl(AlipayConfig.return_url+"?orderId="+merchantOrderId);
        alipayTradePagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户的订单id
        String out_trade_no = merchantOrderId;
        //商店的订单标题
        String subject = "叮叮网订单—付款用户" + merchantUserId;
        //订单金额
        String total_amount = Integer.toString(orderService.seleteOrderPrice(merchantOrderId)/100);
        //订单描述
        String body = subject;
        //订单逾期时间
        String time_expire = TimeUtils.format(new Date().getTime()+1800000);
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                +"\"time_expire\":\""+time_expire+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = defaultAlipayClient.pageExecute(alipayTradePagePayRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }
}
