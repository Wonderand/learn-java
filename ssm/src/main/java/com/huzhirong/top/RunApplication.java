package com.huzhirong.top;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
@MapperScan(value = "com.huzhirong.top.modules.*.mapper")
public class RunApplication {
    private static final Logger logger = Logger.getLogger(RunApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
        logger.info("DemoApplication start!!!");
    }

}
