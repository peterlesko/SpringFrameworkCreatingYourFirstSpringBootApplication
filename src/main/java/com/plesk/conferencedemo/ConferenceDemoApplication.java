package com.plesk.conferencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferenceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceDemoApplication.class, args);
	}
}

//controllers -for API controllers
//models - JPA entities
//repositories -JPA repositories
//services - service or logic based code that may need to go into application
