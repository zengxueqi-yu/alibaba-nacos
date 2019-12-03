package com.july.nacosprovide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者
 * @author zqk
 * @since 2019/12/3
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProvideApplication.class, args);
    }

    @RequestMapping("/test")
    public String test(){
        return "first demo";
    }

}
