package com.cloud.music.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.cloud.music"})
@MapperScan("com.cloud.music.order.mapper")
public class CloudMusicOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMusicOrderApplication.class, args);
    }

}
