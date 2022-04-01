package com.nes.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka114Application {

	public static void main(String[] args) {
		SpringApplication.run(Eureka114Application.class, args);
	}

}
