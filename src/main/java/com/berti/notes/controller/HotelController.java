package com.berti.notes.controller;

import java.util.List;

import com.berti.notes.model.Hotel;
import com.berti.notes.repository.HotelRepository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepo;

    @GetMapping("/api/hotels")
    @ResponseBody
    public List<Hotel> getAll(@RequestParam(name = "query", required = false) String search) {
        if (search != null) {
            return hotelRepo.getHotelLike(search);
        } else {
            return hotelRepo.getAllHotels();
        }
    }

    @GetMapping("/api/hotels/{id}")
    public Hotel getNoteById(@PathVariable(value = "id") int hotelId) {
        try {
            return hotelRepo.getHotelById(hotelId);
        } catch (Exception e) {
            System.out.println("Error fetching record: " + e.getMessage());
        }
        return new Hotel();
    }

    @PostMapping(value = "/api/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public int newHotel(@RequestBody String payload) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(payload);
            JSONObject jsonObject = (JSONObject) object;
            int i = jsonObject.get("id") != null ? Double.valueOf(jsonObject.get("id").toString()).intValue() : 0;
            Hotel h = new Hotel(i, String.valueOf(jsonObject.get("name")), (Long)jsonObject.get("lat"), (Long)jsonObject.get("lon"));
            System.out.println(h);
            return hotelRepo.addHotel(h);
        } catch (Exception e) {
            System.out.println("ParseException" + e.getMessage());
        }
        return 0;
    }



        /**
         * This is hacky, but when you delete a note, the list of notes is returned whether it succeeds or fails 
         * Technically, frontend could check for length to identify/resolve an error in deletion (non-existing note)
         * Orginally I was returning a string here with success or error - but thats not very REST-y
         */
    @DeleteMapping("/api/hotels/{id}")
    @ResponseBody
    public int deleteItem(@PathVariable(required = true, value = "id") int itemId) {
        try {
            return hotelRepo.deleteItem(itemId);
        } catch (Exception e) {
            System.out.println("Error caught during deleting an entry" + e.getMessage());
        }
        return 0;
    }

}
