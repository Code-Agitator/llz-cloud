# web程序基础依赖

## 目录结构

```
org.llz.core.web
│       │   
│       └───LlzSpringApplication.java - 重写SpringApplication   
└─── constant - 常量包
│       │
│       └─── LauncherConstant.java - 启动器常量
└─── execption - 异常类包
│   
└─── service - 服务逻辑包(主要用于SPI扩展)：在启动器逻辑中会执行SPI服务发现，实现在启动器前对配置进行动态修改
```