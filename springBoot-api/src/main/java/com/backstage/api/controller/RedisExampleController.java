package com.backstage.api.controller;

import com.backstage.base.exception.XPFBadRequestException;
import com.backstage.base.response.XPFSingleResponse;
import com.backstage.base.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor jiangcaijun
 * Created by jiangcaijun on 2017/10/27.
 */
@Api(description = "redis测试接口")
@RestController
public class RedisExampleController {

    @Autowired
    private IRedisService redisService;


    @ApiOperation(value = "redis赋值")
    @GetMapping("/redis/set")
    public Object redisSet(@RequestParam("value")String value){
        boolean isOk = redisService.setString("name", value);
        if(isOk){
            return new XPFSingleResponse("redis新增成功");
        }else{
            return new XPFBadRequestException("redis新增失败");
        }
    }


    @ApiOperation(value = "取值")
    @GetMapping("/redis/get")
    public Object redisGet(){
        String value = redisService.getString("name");
        return new XPFSingleResponse("redis获取key为name的：" + value);
    }
}



