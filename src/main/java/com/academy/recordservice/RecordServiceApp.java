package com.academy.recordservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@Slf4j
@SpringBootApplication
// including this base pacakge is important or force the appp to read the security configuration
// otherwise it could skip it and use spring security feature

@ComponentScan(basePackages = "com.macademy.recordmgmt")
public class RecordServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RecordServiceApp.class, args);
    }
}