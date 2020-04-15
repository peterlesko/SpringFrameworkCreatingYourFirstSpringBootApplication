package com.plesk.conferencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferenceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceDemoApplication.class, args);
	}
}

// Previous CRUD calls stopped working when introduced PersistenceConfiguration.java code, which also picks up from application.yml
// only GET call to  http://localhost:5000/ works in Postman,
// after commenting code in  PersistenceConfiguration.java CRUD calls are working again on either 5000 or 8080 ports.

//controllers -for API controllers
//models - JPA entities
//repositories -JPA repositories
//services - service or logic based code that may need to go into application
