package com.impulsofirme.msusuarios.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.impulsofirme.msusuarios.app.entity")
@EnableJpaRepositories(basePackages = "com.impulsofirme.msusuarios.app.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MsusuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsusuariosApplication.class, args);
	}

}
