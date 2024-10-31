# OrderManagementSystem
微服务技术整合： SpringBoot、缓存中间件Redis、消息队列Kafka、注册中心Eureka、服务熔断Hystrix、远程调用OpenFeign、配置中心Config、服务网关Gateway、服务跟踪Sleuth/Zipkin和消息总线Bus。

微服务集合：订单服务order-service、商品服务product-service、库存服务stock-service和用户服务user-service。

### 1. 商品服务product-service-4001
提供接口：查询

### 2. 订单服务order-service-5001
提供接口：查询和新增

### 3. 用户服务user-service-6001
提供接口：查询

### 4. 库存服务stock-service-7001
提供接口：查询和更新

### 5. 缓存Redis
user-service: 查询user信息时先查询Redis，没有缓存时再查询数据库

### 6. 消息队列Kafka
order-service：新增订单后向其他服务发送消息

bus：与bus集成，实现动态刷新Config

### 7. 注册中心Eureka
地址：http://localhost:8001/

### 8. 服务网关gateway
地址：http://localhost:7788/{service}/{id}

### 9. 配置中心Config
地址：https://gitee.com/swaggywilliam/omsconfig.git

### 10. 服务跟踪Sleuth+Zipkin
地址：http://localhost:9411/

