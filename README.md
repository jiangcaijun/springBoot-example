# zeus-parent
* 概述：
    * 后端服务器侧（前后端分离，利用ajax交互）
    * 前台为zeus-ui，参见 https://github.com/jiangcaijun/zeus-ui
#### 1、技术架构
后端以springboot、maven多模块为基础框架，数据库为mysql，实现简单的CRUD功能。前后端以RESTFUL风格的ajax请求来进行交互。

  
#### 2、项目分层

* zeus-api 控制层，主要是各类controller
    * 实现对mysql常见的CRUD请求(PUT、DELETE、PATCH、POST、GET等)，以自定义的Response来返回至客户端（主要体现在 RedisExampleController.java类中）
    * 实现SpringBoot下redis的set与get（主要体现在 RedisExampleController.java类中）
* zeus-base 接口层,包含service接口和entiy实体类  
* zeus-util 工具类层

* 项目代码总体结构如下：

![项目代码总体结构](https://github.com/jiangcaijun/pictureAsset/blob/HEAD/src/zeus-parent/2017-10-30_172632.png?raw=true)
    

#### 3、项目启动
    
项目成功启动时，控制台：
![项目成功启动时，控制台](https://github.com/jiangcaijun/pictureAsset/blob/HEAD/src/zeus-parent/2017-10-30_170546.png?raw=true)
    
#### 4、springboot + redis 相关
* 代码如下：
```
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
```

* 配置如下（路径在 zeus-api\src\main\resources\application-dev.properties）：
```
#REDIS
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=0
```
* redis赋值测试：
http://localhost:7500/zeus/redis/set?value=vic
    
![redis赋值测试](https://github.com/jiangcaijun/pictureAsset/blob/HEAD/src/zeus-parent/2017-10-30_172216.png?raw=true)

* redis取值测试：
http://localhost:7500/zeus/redis/get

![redis赋值测试](https://github.com/jiangcaijun/pictureAsset/blob/HEAD/src/zeus-parent/2017-10-30_172235.png?raw=true)