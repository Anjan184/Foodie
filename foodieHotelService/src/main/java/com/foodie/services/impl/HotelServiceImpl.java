package com.foodie.services.impl;

import com.foodie.DTO.HotelDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.Label;
import com.foodie.entities.MenuItems;
import com.foodie.repositories.HotelRepository;
import com.foodie.services.HotelService;
import com.foodie.services.LabelService;
import com.foodie.services.MenuService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MenuService menuService;

    @Override
    public Hotel createHotel(Hotel hotel) {
        //handling the labels
        if (!hotel.getLabels().isEmpty()) {
            List<Label> labellist = new ArrayList<>();

            for (Label label : hotel.getLabels()) {
                Label getlabel = labelService.getlabelbyDesc(label.getDescription()); //getting the label

                if (getlabel == null) {
                    label.setLabelId(UUID.randomUUID().toString());
                    label = labelService.createLabel(label);
                    labellist.add(label); //adding the labels in the list after creating new label
                } else {
                    labellist.add(getlabel); //adding the labels in the list
                }
            }
            hotel.setLabels(labellist); //setting the list to the hotel
        }

        //setting the id of the hotel
        if (hotel.getHotelId() == null || hotel.getHotelId().isEmpty()) {
            hotel.setHotelId(UUID.randomUUID().toString());
        }

        List<MenuItems> getmenuItems = hotel.getMenuItems();  //getting the menuitems

        // Save the hotel entity first
        hotel.setMenuItems(new ArrayList<>()); //setting the menulist so the persist error doesn't come
        Hotel savedhotel = hotelRepository.save(hotel);

        //handling the menu items
        if (!getmenuItems.isEmpty()) {
            List<MenuItems> menuList = new ArrayList<>();
            for (MenuItems menuItem : getmenuItems) {
                //checking if the menuId is null or not
                if (menuItem.getMenuItemId() == null || menuItem.getMenuItemId().isEmpty()) {
                    menuItem.setMenuItemId(UUID.randomUUID().toString());
                }
                menuItem.setHotel(savedhotel);  //setting the hotel
                menuService.createItems(menuItem);
                menuList.add(menuItem);
            }
            savedhotel.setMenuItems(menuList); // Set the menu items list to the hotel
        }
        return savedhotel;
    }



    @Override
    public List<Hotel> allHotels() {
     return hotelRepository.findAll();
    }

    @Override
    public boolean deleteHotel(String hotelId) {
        Optional<Hotel> gethotel = hotelRepository.findById(hotelId);
        if(gethotel.isPresent()){
            Hotel hotel = gethotel.get();
            //deleting the hotel id from labels
            hotel.getLabels().forEach(label -> {
                try{
                    labelService.deletehotelIdfromLabel(label.getLabelId(),hotelId);
                } catch (Exception ex){
                    ex.printStackTrace();
                    throw new RuntimeException("Cannot delete the hotelId from label");
                }
            });

            hotelRepository.delete(hotel);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Hotel updateHotel(String hotelId, HotelDTO hotelDTO) {
        //getting the hotel
        Optional<Hotel> optionalHotel=hotelRepository.findById(hotelId);

        //checking if the hotel is present
        if(optionalHotel.isPresent()) {
            Hotel existingHotel=optionalHotel.get(); //getting the hotel

            //if the label is not provided
            if(hotelDTO.getLabels() == null){
                hotelDTO.setLabels(new ArrayList<>());
            }

            List<Label> labellist = new ArrayList<>();

            //updating the label list
            if(hotelDTO.getLabels() != null){

                for (Label label : hotelDTO.getLabels()) {
                    Label getlabel = labelService.getlabelbyDesc(label.getDescription()); //getting the label

                    if (getlabel == null) {
                        label.setLabelId(UUID.randomUUID().toString());
                        label = labelService.createLabel(label);
                        labellist.add(label); //adding the labels in the list after creating new label
                    } else {
                        labellist.add(getlabel);
                    }

                }
            }

//            //getting old labels
//            for(Label oldlabel : existingHotel.getLabels()){
//                if(labellist.stream().noneMatch(newLabel -> newLabel.getLabelId().equals(oldlabel.getLabelId()))){
//                    labellist.add(oldlabel);
//                }
//            }

            //removing hotels from the labels
            for(Label oldlabel : existingHotel.getLabels()){
                labelService.deletehotelIdfromLabel(oldlabel.getLabelId(), hotelId);
            }

            //removing the old labels
            existingHotel.getLabels().clear();
            hotelDTO.setLabels(labellist); //setting the list to the hotel

            //mapping the hotelDTO to hotel
            modelMapper.map(hotelDTO, existingHotel);
            return hotelRepository.save(existingHotel);
        } else {
            throw new RuntimeException("Hotel with this id not found "+hotelId);
        }

    }

    public Hotel getHotel(String hotelId){
        Optional<Hotel> gethotel = hotelRepository.findById(hotelId);
        return gethotel.orElse(null);

    }

    @Override
    public List<Hotel> findByLabel(String labelId) {
        return hotelRepository.findByLabels_LabelId(labelId);
    }

}
    
