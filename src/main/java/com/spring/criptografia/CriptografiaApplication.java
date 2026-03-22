package com.spring.criptografia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CriptografiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptografiaApplication.class, args);
	}

}
