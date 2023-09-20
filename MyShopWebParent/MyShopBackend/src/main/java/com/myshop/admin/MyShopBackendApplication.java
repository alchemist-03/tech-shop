package com.myshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.myshop.common"})
public class MyShopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShopBackendApplication.class, args);
    }

}
