package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    // 保存键值对
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 保存键值对
    public void setValueWithExpireTIme(String key, Object value,long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    // 获取键值对
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除键
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
