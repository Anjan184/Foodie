package com.foodie.services.impl;

import com.foodie.DTO.HotelDTO;
import com.foodie.entities.Hotel;
import com.foodie.repositories.HotelRepository;
import com.foodie.services.HotelService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;
    Hotel hotel;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
      hotel.setHotelId(randomHotelId);
       return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> allHotels() {
     return hotelRepository.findAll();
    }

    @Override
    public void deleteHotel(String hotelId) {
        Optional<Hotel> hotel = hotelRepository.findHotelById(hotelId);
        Hotel existingHotel=hotel.get();
        hotelRepository.delete(existingHotel);
    }

    @Transactional
    @Override
    public Hotel updateHotel(String hotelId, HotelDTO hotelDTO) {
            Optional<Hotel> optionalHotel=hotelRepository.findHotelById(hotelId);
            if(optionalHotel.isPresent()) {
                Hotel existingHotel=optionalHotel.get();
                modelMapper.map(hotelDTO, existingHotel);
                return hotelRepository.save(existingHotel);
            }
            else {
                throw new RuntimeException("Hotel with this id not found "+hotelId);
            }
        }

    public Hotel findHotelById(String hotelId){
    return hotelRepository.findHotelById(hotelId).get();
    }
    }
    
