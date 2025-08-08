package com.impulsofirme.msusuarios.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MsusuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsusuariosApplication.class, args);
	}

}
