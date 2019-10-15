package com.example.clientone.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "client-two", fallback = FeignFallBack.class)
public interface FeignService {
    @GetMapping("/test")
    String test();

    @GetMapping("/testFeignInsert")
    String testFeignInsert();

    @GetMapping("/testFeignInsert/{name}")
    String testFeignInsert(@PathVariable("name") String name);
}
