package com.uniandes.tesis.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniandes.tesis.demo.dataaccess.model.Coordinate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UtilsService {

    public String fromListCoordinatesToJson(List<Coordinate> coordinates){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString="";
        try {
            // Convert object to JSON string
            jsonString = objectMapper.writeValueAsString(coordinates);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public List<Coordinate>fromJsonToListCoordinates(String json){
            ObjectMapper objectMapper = new ObjectMapper();
            List<Coordinate> list = new ArrayList<>();
        try {
            // Convert JSON string to object
            list = objectMapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }


}
