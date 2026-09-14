package com.budzet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BudzetApplication {

    public static void main(String[] args) {
        System.out.println("Javachip 입니다.");
        SpringApplication.run(BudzetApplication.class, args);
    }

}
