## 项目简介
SpringCloud 框架学习Demo

## 技术总览
#### 整体架构
    Spring Cloud Greenwich.SR2  
    Spring Boot 2.1.3.RELEASE  
    Spring Platform Cairo-SR7  

#### 项目组件
    Spring Cloud Eureka 注册中心
    Spring Cloud Config 配置中心
    Spring Cloud Gateway 网关中心
    Spring Security OAuth2.0 系统授权
    
    Mybatis Plus+Druid 数据库操作
    spring-data-redis 系统缓存
    
#### 辅助工具
    lombok:1.18.6 快速开发
    hutool-all:4.5.10 java工具类库
    
## 项目环境
MySQL5.5及以上  
Redis 3.2.10   
windows/linux  

## 项目结构：

``` 
|--SpringCloudDemo 父项目模块
|
|------springcloud-eureka 服务发现组件（注册中心）
|
|------springcloud-config 配置服务中心
|
|------springcloud-gateway 服务网关中心
|
|------springcloud-oauth 授权服务器模块
|
|------springcloud-system 系统内容模块
|-         |---system-api 系统相关的一些Entity以及数据库操作
|-         |---system-handle 系统相关的API接口
|-         |---system-admin-server Spring Boot Admin服务监控服务端模块
|
|------springcloud-moudle 业务功能模块
|-         |---springcloud-client 测试客户端
|-         |---springcloud-client 测试客户端2
|
|------springcloud-common 项目通用工具/组成
|-         |---common-core 核心基础代码
|-         |---common-data 数据操作相关工具及配置类（如mybatis配置及druid配置，redis配置等）
|-         |---common-security 系统资源服务器基本内容，包括资源服务器配置，用户信息获取工具类等
|-         |---common-swagger 接口API文档集合基础
``` 
