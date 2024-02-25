package com.vm.brokfree.FirbaseModule;

public class AddressInfo {
    String pin_code;
    String city_name;
    String landmark;
    String locality;
    String apartment_name;
    String room_number;

    public AddressInfo() {
    }

    public AddressInfo(String pin_code, String city_name, String landmark, String locality, String apartment_name, String room_number) {

        this.pin_code = pin_code;
        this.city_name = city_name;
        this.landmark = landmark;
        this.locality = locality;
        this.apartment_name = apartment_name;
        this.room_number = room_number;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }
}
