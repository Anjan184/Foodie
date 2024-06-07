package com.foodie.controllers;

import com.foodie.DTO.HotelDTO;
import com.foodie.entities.Hotel;
import com.foodie.repositories.HotelRepository;
import com.foodie.services.HotelService;
import com.foodie.services.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public ResponseEntity<Hotel> newHotel(@RequestBody Hotel hotel){
       Hotel hotel1= hotelService.createHotel(hotel);
       return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> allHotels(){
        List<Hotel> allHotels=hotelService.allHotels();
        return ResponseEntity.status(HttpStatus.OK).body(allHotels);
    }

    @PutMapping(value="update/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("hotelId") String hotelId, @RequestBody HotelDTO hotelDTO){
     Hotel hotel=hotelService.updateHotel(hotelId,hotelDTO);
    return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    @DeleteMapping(value = "delete/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable("hotelId") String hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel Deleted Successfully");
    }



}
