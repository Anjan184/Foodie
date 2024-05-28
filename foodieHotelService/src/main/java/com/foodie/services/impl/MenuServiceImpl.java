package com.foodie.services.impl;

import com.foodie.DTO.MenuItemsDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.MenuItems;
import com.foodie.repositories.MenuRepository;
import com.foodie.services.MenuService;
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
    MenuItems menuItems;
    Hotel hotel;


    @Override
    public MenuItems createItems(MenuItems menuItems) {
        String randomItemId = UUID.randomUUID().toString();
        menuItems.setMenuId(randomItemId);

        return menuRepository.save(menuItems);
    }

    @Override
    public List<MenuItems> allItems() {
        return menuRepository.findAll();
    }

    @Override
    public void deleteItems(String menuId) {
        Optional<MenuItems> items = menuRepository.findItemsById(menuId);
        MenuItems existingItems=items.get();
        menuRepository.delete(existingItems);
    }

    @Override
    public MenuItems updateItems(String menuId, MenuItemsDTO menuItemsDTO) {
        Optional<MenuItems> optionalItems=menuRepository.findItemsById(menuId);
        if(optionalItems.isPresent()) {
            MenuItems existingItems=optionalItems.get();
            modelMapper.map(menuItemsDTO, existingItems);
            return menuRepository.save(existingItems);
        }
        else {
            throw new RuntimeException("Items with this id not found "+menuId);
        }
    }
}
