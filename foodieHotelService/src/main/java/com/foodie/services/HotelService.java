package com.foodie.services;

import com.foodie.DTO.HotelDTO;
import com.foodie.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    List<Hotel> allHotels();
    boolean deleteHotel(String hotelid);
    Hotel updateHotel(String hotelid, HotelDTO hotelDTO);
    Hotel getHotel(String hotelId);
    List<Hotel> findByLabel(String labelId);
}
