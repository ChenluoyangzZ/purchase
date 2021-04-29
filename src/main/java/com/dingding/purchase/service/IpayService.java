package com.dingding.purchase.service;

import com.dingding.purchase.pojo.Users;
import com.dingding.purchase.pojo.bo.UserBO;
import org.springframework.stereotype.Service;

public interface IpayService {
    public String alipay(String merchantOrderId,String merchantUserId);
}