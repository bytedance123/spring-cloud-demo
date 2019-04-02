# spring cloud demo

## 版本
- SpringBoot: 2.1.0.RELEASE
- SpringCloud: Greenwich.M1

本工程用于展示基于springcloud搭建微服务

## 特性：

1. 基于eureka的注册中心
1. 基于config的配置中心（配置文件集中托管在git）
1. 基于eureka客户端的service
1. 服务调用基于Feign，通用处理已包含再common-lib中
1. 持久层框架使用mybatis-plus，简化配置，并推荐使用注解定义sql

其中多哥service应当集中管理session，推荐使用redis（由于未配置redis的demo环境，所以目前注释掉了相关配置）

## 组件
|名称        | 说明     |
| --------   |  ---- |
| server-eureka        |   注册中心，实现所有服务的注册和发现功能    |
| server-config        |   配置中心，基于git统一管理服务的配置文件    |
| service-demo        |   服务demo，用于书写业务逻辑，包含常见框架满足大部分场景    |
| service-gateway        |   网关服务，面向用户的统一入口，负责实现负载均衡权限校验等操作    |

## 端口号规划
推荐运行方式为独立运行，即每个服务单独分配一个端口号

无论采用jar直接运行，还是采用docker（demo中已配置了docker配置文件），都会涉及到很多服务很多端口号分配的问题，一般规划如下：

- 使用端口网段为8000-8999
- 注册中心：8761
- 配置中心： 8771
- 其他server： 87XX
- 业务service: 88XX

### 说明
1. 多台服务器部署的情况，每台服务器的端口分配应当相同
1. 注册中心可以在每台服务器都部署，以提高集群的可用性
1. 推荐服务器规划hostname并配置dns，便于配置注册中心
1. 目前配置环境分为三种：dev(本地开发)、test(测试环境)、prod(生产环节)

## 运行

```shell
# 先启动注册中心
cd server-eureka & mvn spring-boot:run

# 在启动配置中心
cd server-config & mvn spring-boot:run

# 最后启动service，多个service可以同时启动
cd service-demo & mvn spring-boot:run
```
启动成功后，打开`http://localhost:8800`验证是否启动成功，更多接口见`service-demo`的controller