package com.foodie.services;

import com.foodie.DTO.HotelDTO;
import com.foodie.DTO.MenuItemsDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.MenuItems;

import java.util.List;

public interface MenuService {
    MenuItems createItems(MenuItems menuItems);
    List<MenuItems> allItems();
    boolean deleteItems(String menuId);
    MenuItems updateItems(String menuId, MenuItemsDTO menuItemsDTO);
    MenuItems getMenuItem(String menuId);
    List<MenuItems> getMenuItemsByHotel(String hotelId);
}
