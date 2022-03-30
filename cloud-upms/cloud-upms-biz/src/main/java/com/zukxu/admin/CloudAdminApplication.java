package com.zukxu.admin;

import com.zukxu.common.feign.annotations.EnableCloudFeignClients;
import com.zukxu.common.swagger.annotation.EnableCloudSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCloudSwagger2
@EnableCloudFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }
}
