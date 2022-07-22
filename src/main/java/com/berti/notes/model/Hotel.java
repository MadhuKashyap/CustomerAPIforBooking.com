package com.berti.notes.model;

public class Hotel {

    private int id;
    private String name;
    private long lat;
    private long lon;
    public Hotel() {
    }

    public Hotel(int id, String name, long lat, long lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    @Override public String toString() {
        return "Hotel{" + "id=" + id + ", name='" + name + '\'' + ", lat=" + lat + ", lon=" + lon
            + '}';
    }
}
