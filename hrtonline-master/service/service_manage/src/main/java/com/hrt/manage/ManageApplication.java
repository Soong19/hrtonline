package com.hrt.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hrt"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.hrt.manage.mapper")
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class,args);
    }
}
