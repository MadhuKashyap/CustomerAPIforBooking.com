package com.berti.notes.repository;

import com.berti.notes.model.Address;
import com.berti.notes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepository {
    @Autowired JdbcTemplate template;
    public List<Address> getAllAddress() {
        List<Address> items = template.query("select pincode, city, country from ADDRESS", (result, rowNum) -> new Address(result.getInt("pincode"), result.getString("city"), result.getString("country")));
        return items;
    }

    public List<Address> getAddressLike(String search) {
        List<Address> items = template.query("SELECT * FROM ADDRESS WHERE city LIKE ?", (result, rowNum) -> new Address(result.getInt("pincode"), result.getString("city"), result.getString("country")),
            "%" + search + "%");
        return items;
    }

    public Address getAddressByPincode(int pincode) {
        Address items = template.queryForObject("SELECT * FROM ADDRESS WHERE ID=?", new Object[]{pincode}, (result, rowNum) -> new Address(result.getInt("pincode"), result.getString("city"), result.getString("country")));
        return items;
    }

    public int addAddress(Address a) {
        String query = "INSERT INTO ADDRESS (pincode, city, country) VALUES(?,?,?)";
        return template.update(query, a.getPincode(), a.getCity(), a.getCountry());
    }

    public int deleteAddress(int pincode) {
        String query = "DELETE FROM ADDRESS WHERE PINCODE =?";
        return template.update(query, pincode);
    }
}
