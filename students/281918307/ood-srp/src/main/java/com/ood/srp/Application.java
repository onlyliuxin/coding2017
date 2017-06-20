package com.ood.srp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ajaxfeng on 2017/4/28.
 */
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan("com.ood.srp")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}