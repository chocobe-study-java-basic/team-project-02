package com.budzet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(
        // TODO: MySQL 연동 후, 삭제하기
        exclude = DataSourceAutoConfiguration.class
)
public class BudzetApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudzetApplication.class, args);
    }

}
