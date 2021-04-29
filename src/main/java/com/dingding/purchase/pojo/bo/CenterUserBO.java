package com.dingding.purchase.pojo.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CenterUserBO {
    /**
     * 昵称 呢称
     */
    private String nickname;

    /**
     * 真实姓名 真实姓名
     */
    private String realname;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;
    /**
     * 生日 生日
     */
    private Date birthday;

    /**
     * 手机号 手机号
     */
    private String moblie;

    /**
     * 邮箱地址 邮箱地址
     */
    private String email;
}