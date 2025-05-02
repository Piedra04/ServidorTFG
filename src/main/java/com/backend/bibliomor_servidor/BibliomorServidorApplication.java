package com.backend.bibliomor_servidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.backend.bibliomor_servidor.Models")
public class BibliomorServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliomorServidorApplication.class, args);
	}

}
