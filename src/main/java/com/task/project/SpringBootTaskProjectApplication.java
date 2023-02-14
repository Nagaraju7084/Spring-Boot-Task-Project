package com.task.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootTaskProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskProjectApplication.class, args);
	}

}
