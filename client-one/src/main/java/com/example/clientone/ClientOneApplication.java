package com.example.clientone;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

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
