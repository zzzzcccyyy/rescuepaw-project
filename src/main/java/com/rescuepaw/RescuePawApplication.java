package com.rescuepaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.rescuepaw")
@EntityScan(basePackages = "com.rescuepaw.entity")
@EnableJpaRepositories(basePackages = "com.rescuepaw.repository")
// SpringBootApplication -> 修饰的类 -> 启动类
public class RescuePawApplication {
    public static void main(String[] args) {
        SpringApplication.run(RescuePawApplication.class, args);
    }

}
