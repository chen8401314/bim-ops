# SPRING BOOT JOOQ项目

## 📖 项目介绍
本项目是一个基于Spring Boot 3.x和JOOQ的Java项目模板，提供了一套完整的代码生成器和数据库访问层解决方案。通过使用JOOQ，该项目可以自动生成SQL查询、实体类和DAO，从而简化数据库操作的开发工作。

## 🌟 核心特性

- **test1**：设施设备主数据（资产/设备台账）统一展示
- **test2**：工程模型与单元库管理

## 🛠️ 技术全景

| 领域       | 技术组件                                         |
|----------|----------------------------------------------|
| **核心框架** | Spring Boot 3.x / Spring Cloud 2023          |
| **数据存储** | MySQL 8.0 (OLTP) / Redis (缓存) / MinIO (对象存储) |
| **消息队列** | Kafka                                        |
| **数据处理** | jOOQ / MapStruct                             |
| **文档工具** | SpringDoc OpenAPI 3.0 / Knife4j              |
| **基础设施** | Docker / Kubernetes / Jenkins                |
| **安全认证** | JWT                                          |

## 📂 项目结构

- api-gateway - API网关模块，负责请求路由、权限验证、负载均衡等功能。它作为系统的统一入口，将客户端的请求路由到相应的微服务。
- base-common - 基础公共模块，提供项目中公用的工具类、常量、异常处理、日志记录等。该模块确保了代码复用性和一致性。
- common-service - 公共服务模块，实现了通用的服务组件，如文件上传下载、消息通知、缓存管理等。这些组件可供其他微服务调用。
- custom-generator - 自定义代码生成器模块，基于模板引擎自动生成项目代码，提高开发效率，减少重复性工作。
- eureka - 服务注册与发现模块，使用Netflix Eureka服务器实现服务的自动注册和发现，确保微服务之间的高可用性和可扩展性。
- user-service - 用户服务模块，提供用户管理、角色权限、组织机构管理等核心业务功能。它包括用户认证、授权、信息维护等子功能。

## 🚀 快速启动

### 环境需求

- JDK 21+
- MySQL 8.0
- Redis
- Kafka
- MinIO 或兼容S3的存储服务

## 🔧 质量保障

### 测试策略

- **单元测试**：JUnit 5 + Mockito
- **API测试**：Spring MVC Test 模拟请求

### 代码规范

- 阿里规约
- SonarQube 静态代码分析
