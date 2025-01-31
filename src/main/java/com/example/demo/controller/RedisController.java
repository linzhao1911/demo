package com.example.demo.controller;
import com.example.demo.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    // 测试写入
    @GetMapping("/set")
    public String setKey(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "成功保存键值对：" + key + " -> " + value;
    }

    // 测试读取
    @GetMapping("/get")
    public String getKey(@RequestParam String key) {
        String value = (String)redisService.getValue(key);
        return value != null ? "键 " + key + " 的值是：" + value : "键 " + key + " 不存在！";
    }

    // 测试删除
    @GetMapping("/delete")
    public String deleteKey(@RequestParam String key) {
        redisService.deleteKey(key);
        return "成功删除键：" + key;
    }
}
