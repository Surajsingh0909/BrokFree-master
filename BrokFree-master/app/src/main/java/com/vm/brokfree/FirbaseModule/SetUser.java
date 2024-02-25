package com.vm.brokfree.FirbaseModule;

public class SetUser {
    String first_name;
    String last_name;
    String phone_number;
    String UID;

    public SetUser(String first_name, String last_name, String phone_number, String UID) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.UID = UID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
