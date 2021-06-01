package com.dingding.purchase.controller;

import com.dingding.purchase.pojo.Shopcart;
import com.dingding.purchase.pojo.Users;
import com.dingding.purchase.pojo.bo.ShopCartBO;
import com.dingding.purchase.pojo.bo.UserBO;
import com.dingding.purchase.pojo.vo.ShopCartVO;
import com.dingding.purchase.service.ShopCartService;
import com.dingding.purchase.service.UserService;
import com.dingding.purchase.uitls.CookieUtils;
import com.dingding.purchase.uitls.MD5Uitls;
import com.dingding.purchase.uitls.RedisUtils;
import com.dingding.purchase.uitls.RespondResultUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(tags = "登录注册接口")
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ShopCartController shopCartController;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("账号是否存在")
    @GetMapping("/usernameIsExist")
    @ResponseBody
    public String usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return RespondResultUtils.errorMsg("账号为空");
        }
        boolean existUserName = userService.isExistUserName(username);
        if (existUserName) {
            return RespondResultUtils.errorMsg("账号存在");
        }
        return RespondResultUtils.ok();
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    @SneakyThrows
    public String register(@RequestBody UserBO userBO,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword()) || StringUtils.isBlank(userBO.getConfirmPassword())) {
            return RespondResultUtils.errorMsg("账号或者密码为空");
        }
        boolean existUserName = userService.isExistUserName(userBO.getUsername());
        if (existUserName) {
            return RespondResultUtils.errorMsg("账号存在");
        }
        if (!userBO.getPassword().equals(userBO.getConfirmPassword())) {
            return RespondResultUtils.errorMsg("俩次密码不一样");
        }
        if (userBO.getPassword().length() < 6) {
            return RespondResultUtils.errorMsg("密码不能少于六位数");
        }
        Users user = userService.createUser(userBO);
        Users cookieMsg = frontCookieMsg(user);
        redisUtils.setKeyExp("token:"+user.getId(), UUID.randomUUID().toString().trim());
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "user_token", objectMapper.writeValueAsString(redisUtils.getKey("token"+user.getId())), true);
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "user", objectMapper.writeValueAsString(cookieMsg), true);
        return RespondResultUtils.ok();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    @SneakyThrows
    public String login(@RequestBody UserBO userBO,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {
        if (StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            return RespondResultUtils.errorMsg("账号或者密码为空");
        }
        if (userService.queryUserForLogin(userBO.getUsername(), MD5Uitls.genMD5Str(userBO.getPassword())) == null) {
            return RespondResultUtils.errorMsg("账号或者密码不正确");
        }
        Users users = userService.queryUserForLogin(userBO.getUsername(), MD5Uitls.genMD5Str(userBO.getPassword()));
                Users cookieMsg = frontCookieMsg(users);
        redisUtils.setKeyExp("token:"+users.getId(),UUID.randomUUID().toString().trim());
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "user", objectMapper.writeValueAsString(cookieMsg), true);
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "user_token", objectMapper.writeValueAsString(redisUtils.getKey("token:"+users.getId())), true);
        List<ShopCartBO> shopCartVOList = shopCartController.getShopCartBOS(users.getId());
        CookieUtils.setCookie(httpServletRequest, httpServletResponse, "shopcart", objectMapper.writeValueAsString(shopCartVOList), true);
        return RespondResultUtils.ok();
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public String logout(@RequestParam String userId,
                         HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) {
        CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, "user");
        CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, "shopcart");
        CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, "user_token");
        redisUtils.deleteKey("token:"+userId);
        return RespondResultUtils.ok();
    }

    public Users frontCookieMsg(Users cookieMsg) {
        cookieMsg.setPassword(null);
        cookieMsg.setUpdatedTime(null);
        cookieMsg.setRealname(null);
        cookieMsg.setCreatedTime(null);
        cookieMsg.setBirthday(null);
        cookieMsg.setMobile(null);
        return cookieMsg;
    }
}