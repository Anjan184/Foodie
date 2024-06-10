package com.foodie.repository;

import com.foodie.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    //get orders by userId
    List<Order> findByUserId(String userId) ;

//    //get order by hotelId
//    List<Order> findByHotelId(String hotelId);

}
