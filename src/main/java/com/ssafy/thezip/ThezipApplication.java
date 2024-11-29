package com.ssafy.thezip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ssafy.thezip.**.domain.**")
@SpringBootApplication
public class ThezipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThezipApplication.class, args);
	}

}
