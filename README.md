## 1. txlcn-tm配置启动
### 1.1 下载
```
https://github.com/codingapi/tx-lcn/releases
```

### 1.2 配置

解压后，到
tx-lcn-5.0.2.RELEASE\txlcn-tm\src\main\resources
目录下，修改application.properties配置

```
spring.application.name=tx-manager
server.port=7970

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root

mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.use-generated-keys=true

#tx-lcn.logger.enabled=true
# TxManager Host Ip
#tx-lcn.manager.host=127.0.0.1
# TxClient连接请求端口
#tx-lcn.manager.port=8070
# 心跳检测时间(ms)
#tx-lcn.manager.heart-time=15000
# 分布式事务执行总时间
#tx-lcn.manager.dtx-time=30000
#参数延迟删除时间单位ms
#tx-lcn.message.netty.attr-delay-time=10000
#tx-lcn.manager.concurrent-level=128
# 开启日志
#tx-lcn.logger.enabled=true
#logging.level.com.codingapi=debug

#redis 主机
spring.redis.host=127.0.0.1
#redis 端口
spring.redis.port=6379
#redis 密码
spring.redis.password=
```

到tx-lcn-5.0.2.RELEASE\txlcn-tm
目录下修改pom.xml文件，添加下面的插件
```
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
```

### 1.3 打包jar
```
mvn clean install -Dmaven.test.skip=true
```

### 1.3 启动
```
先启动上面配置的redis
然后运行jar包
```

## 2. 项目配置
### 2.1 添加Maven依赖
```
        <dependency>
            <groupId>com.codingapi.txlcn</groupId>
            <artifactId>txlcn-tc</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.codingapi.txlcn</groupId>
            <artifactId>txlcn-txmsg-netty</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
```

### 2.2 启动类添加注解
添加注解@EnableDistributedTransaction
```
@EnableDistributedTransaction
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.example.clientone.mapper")
@SpringBootApplication
public class ClientOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientOneApplication.class, args);
    }

}
```

### 2.3 添加事务注解
client-one
```
    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public String testFeignInsert() {
        this.feignService.testFeignInsert();
        int zero = 1 / 0;
        return "OK";
    }
    
```

client-two
```
    @Override
    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public void insertTest(String name) {
        testMapper.insertTest(name);
    }
```