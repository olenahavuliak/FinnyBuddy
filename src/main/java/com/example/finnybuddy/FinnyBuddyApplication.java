package com.example.finnybuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FinnyBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinnyBuddyApplication.class, args);
	}

}
