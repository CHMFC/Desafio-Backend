package com.chmfc.desafioComicsApi.desafioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DesafioapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioapiApplication.class, args);
	}

}