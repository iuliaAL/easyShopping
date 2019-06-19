package com.easyShopping.easyShopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EasyShoppingApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasyShoppingApplication.class, args);
	}
}
