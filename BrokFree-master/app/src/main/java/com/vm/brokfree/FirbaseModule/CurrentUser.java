package com.vm.brokfree.FirbaseModule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class CurrentUser {
     String phone_no;
     String first_name;
     String last_name;
     String uuid;


    public CurrentUser(String phone_no, String first_name, String last_name, String uuid) {
        this.phone_no = phone_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.uuid = uuid;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
