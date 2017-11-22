package com.clarkez.unauthedService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class UnauthedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnauthedServiceApplication.class, args);
	}
}
