package com.july.nacosclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 服务消费者
 * @author zqk
 * @since 2019/12/3
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class, args);
    }

    private static final String SERVICE_NAME = "nacos-provide";
    @Resource
    private RestTemplateBuilder restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * @description LoadBalanced用于负载均衡
     * @param
     * @return
     * @author zqk
     * @since 2019/12/3
    */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return restTemplate.build();
    }

    @RequestMapping("/test")
    public String test(){
        ServiceInstance serviceInstance = (ServiceInstance) discoveryClient.getInstances(SERVICE_NAME).get(0);
        String serviceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/test", String.class);
        return serviceResult;
    }

}
