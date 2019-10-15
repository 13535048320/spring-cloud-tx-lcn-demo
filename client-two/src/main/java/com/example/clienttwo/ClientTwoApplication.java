package com.example.clienttwo;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDistributedTransaction
@EnableDiscoveryClient
@MapperScan(basePackages = "com.example.clienttwo.mapper")
@SpringBootApplication
public class ClientTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientTwoApplication.class, args);
    }
}
