package com.foodie.controllers;

import com.foodie.DTO.MenuItemsDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.MenuItems;
import com.foodie.repositories.MenuRepository;
import com.foodie.services.HotelService;
import com.foodie.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<MenuItems> createItems(@RequestBody MenuItems menuItems){
        Hotel hotel = hotelService.findHotelById(menuItems.getHotel().getHotelId());
        menuItems.setHotel(hotel);
        MenuItems menuItems1= menuService.createItems(menuItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItems1);
    }

    @GetMapping
    public ResponseEntity<List<MenuItems>> getAllItems(){
      List<MenuItems> items= menuService.allItems();
      return ResponseEntity.status(HttpStatus.FOUND).body(items);
    }

    @PutMapping(value = "update/{menuId}")
    public ResponseEntity<MenuItems> updateItems(@PathVariable("menuId") String menuId, @RequestBody MenuItemsDTO menuItemsDTO){
        MenuItems menuItems=menuService.updateItems(menuId,menuItemsDTO);
        return ResponseEntity.ok(menuItems);
    }

    @DeleteMapping(value = "delete/{menuId}")
    public ResponseEntity<?> deleteItems(@PathVariable("menuId") String menuId){
        menuService.deleteItems(menuId);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
