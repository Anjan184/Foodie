package com.foodie.controllers;

import com.foodie.DTO.LabelDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.Label;
import com.foodie.services.HotelService;
import com.foodie.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HotelService hotelService;

    //creating the label
    @PostMapping("/create")
    public ResponseEntity<Label> createLabel(@RequestBody Label label){
        return ResponseEntity.ok(labelService.createLabel(label));
    }

    //getting all labels
    @GetMapping
    public ResponseEntity<List<Label>> getAlllabels(){
        return ResponseEntity.ok(labelService.getAllLabels());
    }

    //getting the label by Id
    @GetMapping("/{labelId}")
    public ResponseEntity<Label> getLabel(@PathVariable String labelId){
        Label label = labelService.getSingleLabel(labelId);
        System.out.println("the label is : " +label);
        return ResponseEntity.ok(label) ;
    }

    //deleting the label
    @DeleteMapping("/delete/{labelId}")
    public ResponseEntity<String> deleteLabel(@PathVariable String labelId){
        boolean isDeleted = labelService.deleteLabel(labelId);
        if(isDeleted){
            return ResponseEntity.ok("Label deleted !");
        } else {
            return ResponseEntity.badRequest().body("label not deleted");
        }
    }

    //get the labels by hotel Id
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Label>> getlabelByhotel(@PathVariable String hotelId){
        return ResponseEntity.ok(labelService.getLabelsByHotelId(hotelId));
    }

    //update the label
    @PutMapping("/update/{labelId}")
    public ResponseEntity<Label> updateLabel(@PathVariable String labelId, @RequestBody LabelDTO labelDTO){
        Label label = labelService.updateLabel(labelId,labelDTO);
        return ResponseEntity.ok(label);
    }

    //remove the hotel from the label
    @PutMapping("/removehotel/{labelId}")
    public ResponseEntity<Label> removehotelfromlabel(@PathVariable String labelId,@RequestBody LabelDTO labelDTO){
        Label label = labelService.getSingleLabel(labelId);
        List<Hotel> hotelsToRemove = new ArrayList<>();

        //getting all the hotels
        for(Hotel hotel: label.getHotels()){
            boolean isHotel = labelDTO.getHotels().stream().anyMatch(existHotel -> existHotel.getHotelId().equals(hotel.getHotelId()));
            if(isHotel){
                hotelsToRemove.add(hotel);
            }
        }

        //removing the label from the hotel and hotel from label
        for(Hotel hotel : hotelsToRemove){
            hotel.getLabels().remove(label);
            label.getHotels().remove(hotel);
            hotelService.createHotel(hotel);
        }
        labelService.createLabel(label);
        return ResponseEntity.ok(label);
    }



    //Exception handling
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRunTimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
