package com.berti.notes.repository;

import com.berti.notes.model.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepository {

    @Autowired
    JdbcTemplate template;

    /* Getting all */
    public List<Hotel> getAllHotels() {
        //template.query() here accepts 2 params    1.sql statement   2.rowMapper() impl, since rowMapper is a functional interface with only 1 method 	mapRow(ResultSet rs, int rowNum), we can pass lambda function implementation of mapRow() as argument
        List<Hotel> items = template.query("select id,name,lat,lon from HOTEL", (result,
                rowNum) -> new Hotel(result.getInt("id"), result.getString("name"), result.getLong("lat"), result.getLong("lon")));
        return items;
    }

    /* Getting all name by query string */
    public List<Hotel> getHotelLike(String search) {
        List<Hotel> items = template.query("SELECT * FROM HOTEL WHERE name LIKE ?", (result,
                rowNum) -> new Hotel(result.getInt("id"), result.getString("name"), result.getLong("lat"), result.getLong("lon")),
                "%" + search + "%");
        return items;
    }

    /* Getting by id */
    public Hotel getHotelById(int id) {
        Hotel items = template.queryForObject("SELECT * FROM HOTEL WHERE ID=?", new Object[] {id},
            (result, rowNum) -> new Hotel(result.getInt("id"), result.getString("name"), result.getLong("lat"), result.getLong("lon")));
        return items;

    }

    /* Adding into database table */
    public int addHotel(Hotel h) {
        String query = "INSERT INTO HOTEL (name, lat, lon) VALUES(?,?,?)";
        return template.update(query, h.getName(), h.getLat(), h.getLon());
    }
    /**
     *  get the last note
     */
//    public Hotel lastNote() {
//        Hotel items = template.queryForObject("SELECT * from NOTES WHERE id=(SELECT max(id) FROM NOTES)", (result,
//                rowNum) -> new Hotel(result.getInt("id"), result.getString("name"), result.getLong("lat"), result.getLong("lon")));
//        return items;
//    }

    /* Delete an item */
    public int deleteItem(int id) {
        String query = "DELETE FROM HOTEL WHERE ID =?";
        return template.update(query, id);
    }
}
