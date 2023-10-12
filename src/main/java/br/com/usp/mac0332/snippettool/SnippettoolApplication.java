package br.com.usp.mac0332.snippettool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SnippettoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippettoolApplication.class, args);
	}

}
