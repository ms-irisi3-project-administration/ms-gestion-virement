package com.moisegui.msgestionvirement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsGestionVirementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGestionVirementApplication.class, args);
    }

}
