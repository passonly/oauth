package com.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/4 10:26.
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.oauth.dao.*.*")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
