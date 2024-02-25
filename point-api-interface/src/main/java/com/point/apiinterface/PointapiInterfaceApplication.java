package com.point.apiinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 模拟接口入口类
 */
@SpringBootApplication
@MapperScan("com.point.apiinterface.mapper")
public class PointapiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointapiInterfaceApplication.class, args);
    }

}
