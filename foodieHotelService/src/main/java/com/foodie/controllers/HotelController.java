package com.foodie.controllers;

import com.foodie.DTO.HotelDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.Label;
import com.foodie.services.HotelService;
import com.foodie.services.LabelService;
import com.foodie.services.MenuService;
import com.foodie.repositories.HotelRepository;
import com.foodie.services.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MenuService menuService;


    //create new hotel
    @PostMapping
    public ResponseEntity<Hotel> newHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body( hotelService.createHotel(hotel));
    }

    //get all hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> allHotels(){
        List<Hotel> allHotels=hotelService.allHotels();
        return ResponseEntity.status(HttpStatus.OK).body(allHotels);
    }

    //update hotel
    @PutMapping(value="/update/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("hotelId") String hotelId, @RequestBody HotelDTO hotelDTO){
        Hotel hotel=hotelService.updateHotel(hotelId,hotelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }


    //delete hotel
    @DeleteMapping(value = "/delete/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable("hotelId") String hotelId){
        boolean isdeleted = hotelService.deleteHotel(hotelId);
        if(isdeleted){
            return ResponseEntity.ok("Hotel Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !");
        }

    }

    //Get singe hotel
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    //remove label from the hotel
    @PutMapping("/removelabel/{hotelId}")
    public ResponseEntity<Hotel> removelabelfromHotel(@PathVariable String hotelId, @RequestBody HotelDTO hotelDTO){
        Hotel hotel = hotelService.getHotel(hotelId);  //fetching the hotel
        List<Label > labelsToRemove = new ArrayList<>();

        //getting the labels
        for(Label label : hotel.getLabels()){
            boolean isLabel = hotelDTO.getLabels().stream().anyMatch(labelexits -> labelexits.getLabelId().equals(label.getLabelId()));
            if(isLabel){
                labelsToRemove.add(label);
            }
        }

        //removing the hotel from the label and label from the hotel
        for(Label label : labelsToRemove){
            label.getHotels().remove(hotel);
            hotel.getLabels().remove(label);
            labelService.createLabel(label);
        }
        hotelService.createHotel(hotel);
        return ResponseEntity.ok(hotel);
    }

    //getting the hotel by label
    @GetMapping("/labels/{labelId}")
    public ResponseEntity<List<Hotel>> gethotelfromLabel(@PathVariable String labelId){
        return ResponseEntity.ok(hotelService.findByLabel(labelId));
    }

    //Exception handling
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRunTimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
