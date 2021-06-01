package com.dingding.purchase.uitls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String pattern, String value) {
        stringRedisTemplate.opsForValue().set(pattern, value);
    }

    public  String getKey(String pattern) {
        return stringRedisTemplate.opsForValue().get(pattern);
    }

    public  void deleteKey(String pattern) {
        stringRedisTemplate.delete(pattern);
    }
    public  void  setKeyExp(String pattern,String value){
        stringRedisTemplate.opsForValue().set(pattern,value,3,TimeUnit.HOURS);
    }
    public void  expKey(String key){
        stringRedisTemplate.expire(key,3,TimeUnit.HOURS);
    }
}
