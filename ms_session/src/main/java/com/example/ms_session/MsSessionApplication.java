package com.example.ms_session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSessionApplication.class, args);
    }

}
