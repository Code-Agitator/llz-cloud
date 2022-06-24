# 注解

```
annotation
│
└─── base
│       │
│       └─── BaseAbstractProcessor.java - AbstractProcessor抽象注解处理器
└─── spf - 实现SpringFactoriesAuto注解，用于在编译时自动生成resources/META-INF/spring.factories文件
│       │
│       └───SpringFactoriesAuto.java - 注解
│       └───SpringFactoriesAutoProcessor - 处理器
└─── spi
│       │
│       └─── launcher - 利用SPI机制，启动器启动时执行服务发现与执行启动器服务
│       │       │ 
│       │       └─── LauncherService.java - 启动器服务接口
│       └─── SPI - SPI注解，标识对应的SPI接口       
│       └─── SPIAuto - spi接口的实现类
│       └─── SPIAutoProcessor - 生成resources/META-INF/[spi-interfact-reference] 注册spi实现类
│
└─── web - web相关注解
        │
        └─── CurrentLoginUser.java - 当前登录用户参数注解
```