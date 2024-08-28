package com.example.cloudnative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.cloudnative.repository")
@SpringBootApplication(scanBasePackages={"com.example.cloudnative.*"})
public class CloudNativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudNativeApplication.class, args);
	}

}
