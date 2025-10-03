package com.backoven.catdogshelterserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CatdogshelterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatdogshelterServerApplication.class, args);
    }

}
