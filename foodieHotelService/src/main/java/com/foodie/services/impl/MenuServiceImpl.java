package com.foodie.services.impl;

import com.foodie.DTO.MenuItemsDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.MenuItems;
import com.foodie.repositories.MenuRepository;
import com.foodie.services.MenuService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public MenuItems createItems(MenuItems menuItems) {
        String randomItemId = UUID.randomUUID().toString();
        menuItems.setMenuItemId(randomItemId);
        return menuRepository.save(menuItems);
    }

    @Override
    public List<MenuItems> allItems() {
        return menuRepository.findAll();
    }

    @Override
    public boolean deleteItems(String menuItemId) {
        Optional<MenuItems> items = menuRepository.findById(menuItemId);
        if(items.isPresent()){
            MenuItems existingItems=items.get();
            menuRepository.delete(existingItems);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public MenuItems updateItems(String menuItemId, MenuItemsDTO menuItemsDTO) {
        Optional<MenuItems> optionalItems=menuRepository.findById(menuItemId);
        if(optionalItems.isPresent()) {
            MenuItems existingItems=optionalItems.get();
            modelMapper.map(menuItemsDTO, existingItems);
            return menuRepository.save(existingItems);
        }
        else {
            throw new RuntimeException("Items with this id not found "+menuItemId);
        }
    }

    @Override
    public MenuItems getMenuItem(String menuItemId) {
        Optional<MenuItems> getmenu = menuRepository.findById(menuItemId);
        return getmenu.orElse(null);
    }

    @Override
    public List<MenuItems> getMenuItemsByHotel(String hotelId) {
        return menuRepository.findByHotel_HotelId(hotelId);
    }
}
