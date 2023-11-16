package com.ssafy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = { "com.ssafy.**.mapper" })
public class Pjt07Buk041003Application {

	public static void main(String[] args) {
		SpringApplication.run(Pjt07Buk041003Application.class, args);
	}
}
