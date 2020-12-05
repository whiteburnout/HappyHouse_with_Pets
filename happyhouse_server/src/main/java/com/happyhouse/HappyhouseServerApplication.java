package com.happyhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.happyhouse.mapper"})
public class HappyhouseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyhouseServerApplication.class, args);
	}

}
