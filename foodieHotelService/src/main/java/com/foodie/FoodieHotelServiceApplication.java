package com.foodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FoodieHotelServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodieHotelServiceApplication.class, args);
	}

}
