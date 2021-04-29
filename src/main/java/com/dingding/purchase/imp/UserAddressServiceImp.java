package com.dingding.purchase.imp;

import com.dingding.purchase.Enum.IsDefaultEnum;
import com.dingding.purchase.mapper.UserAddressMapper;
import com.dingding.purchase.pojo.UserAddress;
import com.dingding.purchase.service.UserAddressService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserAddressServiceImp implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> queryUserAllAddress(String userId) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return userAddressMapper.selectByExample(example);
    }

    @Override
    public void addUserAddress(UserAddress userAddress) {
        userAddressMapper.insert(userAddress);
    }

    @Override
    @SneakyThrows
    public void delUserAddress(String userId, String addressId) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("addressId", addressId);
        userAddressMapper.deleteByExample(example);
    }

    @Override
    public void updateUserAddress(UserAddress userAddressList) {
       userAddressMapper.updateByPrimaryKey(userAddressList);
    }

    @Override
    public void updateUserAddressDefault(String userId, String addressId) {
        int idDefault = IsDefaultEnum.YES.type;
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<UserAddress> userAddresses = userAddressMapper.selectByExample(example);
        for (UserAddress userAddress : userAddresses) {
            if (userAddress.getId().equals(addressId)) {
                userAddress.setIsDefault(idDefault);
                userAddressMapper.updateByPrimaryKeySelective(userAddress);
            } else {
                userAddress.setIsDefault(IsDefaultEnum.NO.type);
                userAddressMapper.updateByPrimaryKeySelective(userAddress);
            }
        }
    }

    @Override
    public UserAddress queryUserOneAddress(String userId, String addressId) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("addressId", addressId);
        return userAddressMapper.selectOneByExample(example);
    }
}
