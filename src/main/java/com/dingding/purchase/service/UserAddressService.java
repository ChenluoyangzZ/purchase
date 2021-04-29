package com.dingding.purchase.service;

import com.dingding.purchase.pojo.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserAddressService {
    public List<UserAddress> queryUserAllAddress(String userId);

    public void addUserAddress(UserAddress userAddressList);

    public void delUserAddress(String userId,String addressId);

    public void updateUserAddress(UserAddress userAddressList);

    public void updateUserAddressDefault(String userId,String addressId);

    public UserAddress queryUserOneAddress(String userId,String addressId);
}
