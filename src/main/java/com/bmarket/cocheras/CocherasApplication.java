package com.bmarket.cocheras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class CocherasApplication extends ServletInitializer {

	
	
	public static void main(String[] args) {
		SpringApplication.run(CocherasApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CocherasApplication.class);
	}

}
