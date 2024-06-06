package com.foodie.services;

import com.foodie.DTO.LabelDTO;
import com.foodie.entities.Label;

import java.util.List;

public interface LabelService {

    //create label
    Label createLabel(Label label);
    //getting all labels
    List<Label> getAllLabels();
    //updating the label
    Label updateLabel(String labelId, LabelDTO labelDTO);
    //deleting label
    Boolean deleteLabel(String labelId);
    //getting label by hotelId
    List<Label> getLabelsByHotelId(String hotelId);
    //getting single label
    Label getSingleLabel(String labelId);
    //getting label by description
    Label getlabelbyDesc(String des);
    //deleting hotelId from label
    boolean deletehotelIdfromLabel(String labelId, String hotelId);

}
