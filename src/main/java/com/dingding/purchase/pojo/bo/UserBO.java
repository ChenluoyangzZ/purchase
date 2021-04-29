package com.dingding.purchase.pojo.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBO {
    /**
     * 账号信息 用户名字
     */
    private String username;
    /**
     * 账号信息 用户密码
     */
    private String password;
    /**
     * 账号信息 用户二次密码
     */
    private String confirmPassword;
}
