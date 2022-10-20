package com.chaojilaji.hy.developutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.chaojilaji.hyutils.dbcore","com.chaojilaji.hyutils.db","com.chaojilaji.hy"})
public class DeveloputilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeveloputilsApplication.class, args);
    }

}
