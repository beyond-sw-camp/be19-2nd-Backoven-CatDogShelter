package com.backoven.catdogshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CatDogShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatDogShelterApplication.class, args);
    }

}
