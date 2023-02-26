# 服务包
### 目录结构
```
llz-cloud-service
│
└─── gatewat-service - 网关
│
└─── model*-service - 具体模块服务
        │
        └─── api - 封装对外的api接口(openfeign)
        │
        └─── web - 服务
        │
        └─── core - 封装api和web公用的实体类(pojo vo dto constant等)
```