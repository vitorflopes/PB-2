package com.example.consulta_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class ConsultaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaServiceApplication.class, args);
	}

}
