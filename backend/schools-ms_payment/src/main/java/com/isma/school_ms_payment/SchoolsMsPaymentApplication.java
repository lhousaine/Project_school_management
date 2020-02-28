package com.isma.school_ms_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SchoolsMsPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolsMsPaymentApplication.class, args);
    }

}
