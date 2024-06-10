package com.foodie.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FoodieServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieServiceRegistryApplication.class, args);
	}

}
