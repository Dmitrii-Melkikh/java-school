package ru.croc.task16;

import java.util.HashSet;
import java.util.Set;

public class Driver {

    private String id;
    private double latitude, longitude;
    private String comfortClass;
    private Set<String> specialRequests;

    public Driver (double latitude, double longitude, String comfortClass, Set<String> specialRequests, String id){
        this.latitude = latitude;
        this.longitude = longitude;
        this.comfortClass = comfortClass;
        this.specialRequests = specialRequests;
        this.id = id;
    }

    public String getComfortClass() {
        return comfortClass;
    }


    public double getLatitude() {
        return latitude;
    }



    public double getLongitude() {
        return longitude;
    }



    public Set<String> getSpecialRequests() {
        return specialRequests;
    }

    public String getId() {
        return id;
    }
    @Override
    public String toString(){
        return id;
    }
}
