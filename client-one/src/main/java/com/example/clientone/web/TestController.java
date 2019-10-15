package com.example.clientone.web;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.clientone.feign.FeignService;
import com.example.clientone.service.ITestService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class TestController {

    @Autowired
    private FeignService feignService;

    @Autowired
    private ITestService testService;

    @Value("${client.text}")
    private String text;

    @GetMapping("/test")
    public String test() {
        return this.text;
    }

    @GetMapping("testInsert")
    public String testInsert() {
        this.testService.insertTest("zzc");
        return "OK";
    }

    @GetMapping("/testFeign")
    public String testFeign() {
        return this.feignService.test();
    }

    @GetMapping("/testFeignInsert")
    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    public String testFeignInsert() {
        this.feignService.testFeignInsert();
//        int zero = 1 / 0;
        return "OK";
    }

    @GetMapping("/testRibbon")
    public String testRibbon(){
        return "OK";
    }
}
