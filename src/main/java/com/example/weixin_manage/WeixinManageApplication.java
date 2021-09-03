package com.example.weixin_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.weixin_manage.mapper")
public class WeixinManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinManageApplication.class, args);
    }

}
