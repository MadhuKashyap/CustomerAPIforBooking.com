package com.berti.notes.controller;

import com.berti.notes.model.User;
import com.berti.notes.repository.HotelRepository;
import com.berti.notes.repository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/api/users")
    public List<User> getAllUsers(@RequestParam(name = "query", required = false) String search)
    {
        if (search != null) {
            System.out.println("ddd");
            return userRepo.getUserLike(search);
        } else {
            return userRepo.getAllUsers();
        }
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable(value = "id") int userId)
    {
        try{
            return userRepo.getUserById(userId);
        } catch (Exception e) {
            System.out.println("Error encountered while fetching user by id= " + userId +"due to" +e.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public int newUser(@RequestBody String payload) {
//        JSONObject json = new JSONObject();
        JSONParser jp = new JSONParser();
        try {
            JSONObject output = (JSONObject) jp.parse(payload);
            int id = output.get("id") != null ? Integer.parseInt(output.get("id").toString()) : 0;
            return userRepo.addUser(new User(id, String.valueOf(output.get("name")), Integer.parseInt(output.get("pincode").toString())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public int deleteItem(@PathVariable(required = true, value = "id") int itemId) {
        try {
            return userRepo.deleteUser(itemId);
        } catch (Exception e) {
            System.out.println("Error caught during deleting an entry" + e.getMessage());
        }
        return 1;
    }
}
