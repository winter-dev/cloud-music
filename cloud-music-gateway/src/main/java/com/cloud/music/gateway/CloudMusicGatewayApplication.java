package com.cloud.music.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CloudMusicGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMusicGatewayApplication.class, args);
    }
}
