package ru.example.altarixtesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AltarixtesttaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltarixtesttaskApplication.class, args);
	}

}
