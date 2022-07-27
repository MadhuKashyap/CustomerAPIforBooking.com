package com.berti.notes.controller;

import com.berti.notes.model.Address;
import com.berti.notes.model.User;
import com.berti.notes.repository.AddressRepository;
import com.berti.notes.repository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add")
public class AddressController {
    @Autowired AddressRepository addRepo;

    @GetMapping("/api/users")
    public List<Address> getAllUsers(@RequestParam(name = "query", required = false) String search)
    {
        if (search != null) {
            System.out.println("ddd");
            return addRepo.getAddressLike(search);
        } else {
            return addRepo.getAllAddress();
        }
    }

    @GetMapping("/api/users/{id}")
    public Address getUserById(@PathVariable(value = "pincode") int id)
    {
        try{
            return addRepo.getAddressByPincode(id);
        } catch (Exception e) {
            System.out.println("Error encountered while fetching user by id= " + id +"due to" +e.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/api/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public int newUser(@RequestBody String payload) {
        //        JSONObject json = new JSONObject();
        JSONParser jp = new JSONParser();
        try {
            JSONObject output = (JSONObject) jp.parse(payload);
            int pincode = output.get("pincode") != null ? Integer.parseInt(output.get("pincode").toString()) : 0;
            return addRepo.addAddress(new Address(pincode, String.valueOf(output.get("city")), String.valueOf(output.get("country"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public int deleteItem(@PathVariable(required = true, value = "id") int itemId) {
        try {
            return addRepo.deleteAddress(itemId);
        } catch (Exception e) {
            System.out.println("Error caught during deleting an entry" + e.getMessage());
        }
        return 1;
    }
}
