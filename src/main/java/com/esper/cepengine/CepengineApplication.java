package com.esper.cepengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Import(Starter.class)
@SpringBootApplication
public class CepengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepengineApplication.class, args);
	}

}
