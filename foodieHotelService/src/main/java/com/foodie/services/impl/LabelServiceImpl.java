package com.foodie.services.impl;

import com.foodie.DTO.LabelDTO;
import com.foodie.entities.Hotel;
import com.foodie.entities.Label;
import com.foodie.repositories.HotelRepository;
import com.foodie.repositories.LabelRepository;
import com.foodie.services.HotelService;
import com.foodie.services.LabelService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HotelRepository hotelRepository;

//    @Autowired
//    private HotelService hotelService;

    @Override
    public Label createLabel(Label label) {
        //error message
        Label existingLabel = labelRepository.findByDescription(label.getDescription());
        if(existingLabel != null){
            throw new RuntimeException("Label with the same name exists !");
        }

        if(label.getLabelId() == null || label.getLabelId().isEmpty()){
            String randomlabelId = UUID.randomUUID().toString();
            label.setLabelId(randomlabelId);
        }
        Label label1 = labelRepository.save(label);

        for(Hotel hotel : label.getHotels()){ //setting the label into the hotel
            Hotel existingHotel = hotelRepository.findById(hotel.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel Not Found !"));
            if (!existingHotel.getLabels().contains(label1)) {
                existingHotel.getLabels().add(label1);
                hotelRepository.save(existingHotel);
            }
        }
        return label1;
    }

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @Transactional
    @Override
    public Label updateLabel(String labelId, LabelDTO labelDTO) {
        Optional<Label> optionalLabel = labelRepository.findById(labelId);
        if(optionalLabel.isPresent()){
            Label label = optionalLabel.get(); //getting the label

            if(labelDTO.getHotels() == null){     //if the hotel is not provided
                List<Hotel> hotellist = new ArrayList<>();
                labelDTO.setHotels(hotellist);
            }

            //setting the hotels
            if(labelDTO.getHotels() != null || !labelDTO.getHotels().isEmpty()){
                List<Hotel> hotelList = new ArrayList<>();
                //setting the labels
                for(Hotel hotel : labelDTO.getHotels()){
                    Hotel getHotel = hotelRepository.findById(hotel.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel with id:" + hotel.getHotelId() + " not found"));

                    //iterating the list to check if the hotel is already present
                    boolean hotelExist = label.getHotels().stream().anyMatch(existhotel -> existhotel.getHotelId().equals(getHotel.getHotelId()));
                    if(!hotelExist){
                        hotelList.add(hotel);
                    }
                }
                labelDTO.setHotels(hotelList);
            }

            //saving the label
            modelMapper.map(labelDTO, label); //mapping the DTO to the object to update the label
            Label savedLabel = labelRepository.save(label);

            //setting the label in the hotels
            for(Hotel hotel: labelDTO.getHotels()){
                Hotel existingHotel = hotelRepository.findById(hotel.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel Not Found !"));
                existingHotel.getLabels().add(savedLabel);
                hotelRepository.save(existingHotel);
            }
            return savedLabel;
        } else {
            throw new RuntimeException("Label with Id: " +labelId+ " not found !");
        }
    }


    @Override
    @Transactional
    public Boolean deleteLabel(String labelId) {
        Optional<Label> exLabel = labelRepository.findById(labelId);
        if(exLabel.isPresent()){
            Label label = exLabel.get();
            //removing the labels form hotels
            label.getHotels().forEach(hotel -> hotel.getLabels().remove(label));
            labelRepository.delete(label);
            return true;
        }
        return false;
    }

    @Override
    public List<Label> getLabelsByHotelId(String hotelId) {
        return labelRepository.findByHotels_HotelId(hotelId);
    }

    @Override
    public Label getSingleLabel(String labelId) {
        Optional<Label> getlabel = labelRepository.findById(labelId);
        if(getlabel.isPresent()){
            return getlabel.get();
        }else{
            throw new RuntimeException("label with Id: " + labelId + " not found !");
        }

    }

    @Override
    public Label getlabelbyDesc(String des) {

        return labelRepository.findByDescription(des);
    }

    @Override
    @Transactional
    public boolean deletehotelIdfromLabel(String labelId, String hotelId) {
        //fetching the label
        Optional<Label> getLabel = labelRepository.findById(labelId);
        if(getLabel.isPresent()){
            Label label = getLabel.get();
            List<Hotel> hotels = label.getHotels();
            //removing the hotel id
            hotels.removeIf(hotel -> hotel.getHotelId().equals(hotelId));
            //saving the label
            labelRepository.save(label);
            return true;
        }
        return false;
    }
}
