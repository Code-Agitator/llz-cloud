# 自己玩的微服务

## 目录结构

```
根目录  
│
└─── llz-cloud-common - 封装内核工具 例如mybatis等
│
└─── llz-cloud-service - 服务包  
│ 
└─── llz-spring-cloud-dependencies - 依赖版本统一管理
```

### 部署xxl-job

```shell
docker run -p 8880:8080 -e PARAMS="--spring.datasource.url=jdbc:mysql://192.168.2.7:3306/xxl_job?Unicode=true&characterEncoding=UTF-8&useSSL=false --spring.datasource.password=root 
--xxl.job.accessToken=accessToken" -v /root/tmp:/data/applogs --name xxl-job-admin  -d xuxueli/xxl-job-admin:2.3.0
```

