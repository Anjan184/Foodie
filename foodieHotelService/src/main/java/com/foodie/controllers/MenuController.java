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

    //creating the menu item
    @PostMapping
    public ResponseEntity<MenuItems> createItems(@RequestBody MenuItems menuItems){
        MenuItems menuItems1= menuService.createItems(menuItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItems1);
    }

    //getting all the items
    @GetMapping
    public ResponseEntity<List<MenuItems>> getAllItems(){
      List<MenuItems> items= menuService.allItems();
      return ResponseEntity.status(HttpStatus.FOUND).body(items);
    }

    //updating the menu
    @PutMapping(value = "/update/{menuId}")
    public ResponseEntity<MenuItems> updateItems(@PathVariable("menuId") String menuId, @RequestBody MenuItemsDTO menuItemsDTO){
        MenuItems menuItems=menuService.updateItems(menuId,menuItemsDTO);
        return ResponseEntity.ok(menuItems);
    }

    //deleting the menu
    @DeleteMapping(value = "/delete/{menuId}")
    public ResponseEntity<?> deleteItems(@PathVariable("menuId") String menuId){
        boolean isdeleted = menuService.deleteItems(menuId);
        if(isdeleted){
            return ResponseEntity.ok().body("Menu item is deleted");
        }else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    //getting single menuitems
    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItems> getMenuItem(@PathVariable String menuItemId){
        return ResponseEntity.ok(menuService.getMenuItem(menuItemId));
    }

    //Exception handling
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRunTimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
