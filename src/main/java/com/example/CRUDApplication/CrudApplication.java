package com.example.CRUDApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudApplication {

	 static void main(String[] args) {

        SpringApplication.run(CrudApplication.class, args);

     }
}
