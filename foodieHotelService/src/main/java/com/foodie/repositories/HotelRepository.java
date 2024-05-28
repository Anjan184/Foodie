package com.foodie.repositories;

import com.foodie.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,String > {

    @Query("select h from Hotel h where h.hotelId=:hotelId")
    public Optional<Hotel> findHotelById(@Param("hotelId") String hotelId);


}
