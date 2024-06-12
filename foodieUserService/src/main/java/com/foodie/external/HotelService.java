package com.foodie.external;

import com.foodie.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import com.foodie.Config.FeignClientConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@FeignClient(name = "FOODIE-HOTEL-SERVICE", configuration = FeignClientConfiguration.class)
public interface HotelService {

    @GetMapping("/hotel")
    List<Hotel> getAllHotel();

    @GetMapping("/hotel/{hotelId}")
    Hotel getSingleHotel(@PathVariable("hotelId") String hotelId);

    @PutMapping("/hotel/update/{hotelId}")
    Hotel updateHotel(@PathVariable String hotelId);

    @DeleteMapping("/hotel/delete/{hotelId}")
    Boolean deleteHotel(@PathVariable String hotelId);
}
