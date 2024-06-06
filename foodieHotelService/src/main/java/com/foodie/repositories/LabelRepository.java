package com.foodie.repositories;

import com.foodie.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, String> {

    List<Label> findByHotels_HotelId(String hotelId);
    Label findByDescription(String des);
}
