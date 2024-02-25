package com.vm.brokfree.FirbaseModule;

import java.util.ArrayList;
import java.util.List;

public class PostDataInformation {
    String no_of_balconies;
    String no_of_bedRooms;
    String no_of_bathRooms;
    String area;
    String open_parking;
    String cover_parking;
    String furnished;
    List<String> other_rooms = new ArrayList<>();

    public PostDataInformation() {
    }

    public PostDataInformation(String noOfBalconies, String noOfBedRooms, String noOfBathRooms, String area, String openParking, String coverParking, String furnished, List<String> otherRooms) {
        this.no_of_balconies = noOfBalconies;
        this.no_of_bedRooms = noOfBedRooms;
        this.no_of_bathRooms = noOfBathRooms;
        this.area = area;
        this.open_parking = openParking;
        this.cover_parking = coverParking;
        this.furnished = furnished;
        this.other_rooms = otherRooms;
    }

    public String getNo_of_balconies() {
        return no_of_balconies;
    }

    public void setNo_of_balconies(String no_of_balconies) {
        this.no_of_balconies = no_of_balconies;
    }

    public String getNo_of_bedRooms() {
        return no_of_bedRooms;
    }

    public void setNo_of_bedRooms(String no_of_bedRooms) {
        this.no_of_bedRooms = no_of_bedRooms;
    }

    public String getNo_of_bathRooms() {
        return no_of_bathRooms;
    }

    public void setNo_of_bathRooms(String no_of_bathRooms) {
        this.no_of_bathRooms = no_of_bathRooms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOpen_parking() {
        return open_parking;
    }

    public void setOpen_parking(String open_parking) {
        this.open_parking = open_parking;
    }

    public String getCover_parking() {
        return cover_parking;
    }

    public void setCover_parking(String cover_parking) {
        this.cover_parking = cover_parking;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public List<String> getOther_rooms() {
        return other_rooms;
    }

    public void setOther_rooms(List<String> other_rooms) {
        this.other_rooms = other_rooms;
    }
}
