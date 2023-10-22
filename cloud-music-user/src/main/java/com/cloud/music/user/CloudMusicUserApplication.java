package com.cloud.music.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cloud.music"})
@MapperScan("com.cloud.music.user.mapper")
public class CloudMusicUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMusicUserApplication.class, args);
    }
}
