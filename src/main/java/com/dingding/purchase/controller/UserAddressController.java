package com.dingding.purchase.controller;

import com.dingding.purchase.Enum.IsDefaultEnum;
import com.dingding.purchase.pojo.UserAddress;
import com.dingding.purchase.pojo.bo.UserAddressBO;
import com.dingding.purchase.service.UserAddressService;
import com.dingding.purchase.uitls.RespondResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "用户地址接口")
@RequestMapping("/address")
@RestController
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation("查询地址")
    @PostMapping("/list")
    public String getUserAddress(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return RespondResultUtils.errorMsg("没有传入userId");
        }
        return RespondResultUtils.ok(userAddressService.queryUserAllAddress(userId));
    }

    @ApiOperation("添加地址")
    @PostMapping("/add")
    public String addUserAddress(@RequestBody UserAddressBO userAddressBo) {
        int isDefault = IsDefaultEnum.NO.type;
        List<UserAddress> userAddresses = userAddressService.queryUserAllAddress(userAddressBo.getUserId());
        if (userAddresses == null || userAddresses.isEmpty()) {
            isDefault = IsDefaultEnum.YES.type;
        }
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(userAddressBo, address);
        address.setId(Sid.nextShort());
        address.setCreatedTime(new Date());
        address.setUpdatedTime(new Date());
        address.setIsDefault(isDefault);
        userAddressService.addUserAddress(address);
        return RespondResultUtils.ok();
    }

    @ApiOperation("删除地址")
    @PostMapping("/delete")
    public String addUserAddress(@RequestParam String userId, String addressId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return RespondResultUtils.errorMsg("参数为空");
        }
        userAddressService.delUserAddress(userId, addressId);
        return RespondResultUtils.ok();
    }

    @ApiOperation("更新地址")
    @PostMapping("/update")
    public String updateUserAddress(@RequestBody UserAddressBO userAddressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO, userAddress);
        userAddress.setId(userAddressBO.getAddressId());
        userAddress.setUpdatedTime(new Date());
        userAddressService.updateUserAddress(userAddress);
        return RespondResultUtils.ok();
    }

    @ApiOperation("设置为默认地址")
    @PostMapping("/setDefault")
    public String updateUserAddressDefault(@RequestParam String userId, String addressId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return RespondResultUtils.errorMsg("参数为空");
        }
        userAddressService.updateUserAddressDefault(userId, addressId);
        return RespondResultUtils.ok();
    }
}