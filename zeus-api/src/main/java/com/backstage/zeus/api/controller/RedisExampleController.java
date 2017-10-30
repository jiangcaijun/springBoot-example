package com.backstage.zeus.api.controller;

import com.backstage.zeus.base.exception.XPFBadRequestException;
import com.backstage.zeus.base.response.XPFSingleResponse;
import com.backstage.zeus.base.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor jiangcaijun
 * Created by jiangcaijun on 2017/10/27.
 */
@RestController
public class RedisExampleController {


    @Autowired
    private IRedisService redisService;


    @RequestMapping("/redis/set")
    public Object redisSet(@RequestParam("value")String value){
        boolean isOk = redisService.setString("name", value);
        if(isOk){
            return new XPFSingleResponse("redis新增成功");
        }else{
            return new XPFBadRequestException("redis新增失败");
        }
    }

    @RequestMapping("/redis/get")
    public Object redisGet(){
        String name = redisService.getString("name");
        return new XPFSingleResponse("redis获取：" + name);
    }
}
