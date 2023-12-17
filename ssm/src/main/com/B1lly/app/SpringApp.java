package com.B1lly.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.B1lly.app.DAO")
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }
}
