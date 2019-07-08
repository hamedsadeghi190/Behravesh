package com.partgah.behravesh.Models;

import android.location.Location;


public class locationModel {
    private Location location;
    private String Address;

    public locationModel(Location location) {
        this.location = location;
    }

    public locationModel(Location location, String address) {
        this.location = location;
        Address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
