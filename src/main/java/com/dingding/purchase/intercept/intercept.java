package com.dingding.purchase.intercept;

import com.dingding.purchase.pojo.Users;
import com.dingding.purchase.uitls.CookieUtils;
import com.dingding.purchase.uitls.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class intercept implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isNotBlank(CookieUtils.getCookieValue(request, "user_token", true))
                && StringUtils.isNotBlank(CookieUtils.getCookieValue(request, "user", true))) {
            System.out.println("登陆了捏");
            String user=CookieUtils.getCookieValue(request, "user", true);
            String token = "\"" + redisUtils.getKey("token:" + objectMapper.readValue(user, Users.class).getId()) + "\"";
            if(Objects.equals(CookieUtils.getCookieValue(request, "user_token", true), token)){
                return true;
            }else {
                CookieUtils.setCookie(request,response,"user","",true);
                CookieUtils.setCookie(request,response,"user_token","",true);
                CookieUtils.setCookie(request,response,"shopcart","",true);
                return false;
            }
        } else {
            System.out.println("拦截了捏");
            CookieUtils.setCookie(request,response,"user","",true);
            CookieUtils.setCookie(request,response,"user_token","",true);
            CookieUtils.setCookie(request,response,"shopcart","",true);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }
}
