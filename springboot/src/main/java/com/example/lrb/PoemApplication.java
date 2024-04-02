package com.example.lrb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.example.lrb.dao")
public class PoemApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PoemApplication.class, args);
    }

}
