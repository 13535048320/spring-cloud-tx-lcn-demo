package com.example.ribbon.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/testRibbon")
    public String testRibbon(){
        this.loadBalancerClient.choose("client-one");
        String info = restTemplate.getForObject("http://client-one/testRibbon",String.class);
        return info;
    }

    @GetMapping("/testRibbon/{id}")
    @HystrixCommand(fallbackMethod = "ribbonErrorFallback")
    public String testRibbon(@PathVariable("id") int id){
        this.loadBalancerClient.choose("client-one");
        String info = restTemplate.getForObject("http://client-one/testRibbon",String.class);
        return info + ",Id: " + id;
    }

    public String ribbonErrorFallback(int id){
        System.out.println("ErrorId: " + id);
        return "Error!Id: " + id;
    }
}
