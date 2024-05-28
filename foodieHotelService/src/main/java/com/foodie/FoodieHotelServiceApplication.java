package com.foodie;

import com.foodie.DTO.MenuItemsDTO;
import com.foodie.entities.MenuItems;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class FoodieHotelServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodieHotelServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration()
				.setPropertyCondition(Conditions.isNotNull())
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
		modelMapper.createTypeMap(MenuItemsDTO.class, MenuItems.class)
				.addMappings(mapping->mapping.skip(MenuItems::setHotel));
		return modelMapper;
	}

}
