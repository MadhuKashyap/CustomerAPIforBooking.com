package com.berti.notes.repository;

import com.berti.notes.model.Hotel;
import com.berti.notes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired JdbcTemplate template;

    public List<User> getAllUsers() {
        List<User> items = template.query("select id, name, pincode from USER", (result, rowNum) -> new User(result.getInt("id"), result.getString("name"), result.getInt("pincode")));
        return items;
    }

    public List<User> getUserLike(String search) {
        List<User> items = template.query("SELECT * FROM USER WHERE name LIKE ?", (result, rowNum) -> new User(result.getInt("id"), result.getString("name"), result.getInt("pincode")),
            "%" + search + "%");
        return items;
    }

    public User getUserById(int id) {
        User items = template.queryForObject("SELECT * FROM USER WHERE ID=?", new Object[]{id}, (result, rowNum) -> new User(result.getInt("id"), result.getString("name"), result.getInt("pincode")));
        return items;
    }

    public int addUser(User h) {
        String query = "INSERT INTO USER (id, name, pincode) VALUES(?,?,?)";
        return template.update(query, h.getId(), h.getName(), h.getPincode());
    }

    public int deleteUser(int id) {
        String query = "DELETE FROM USER WHERE ID =?";
        return template.update(query, id);
    }
}
