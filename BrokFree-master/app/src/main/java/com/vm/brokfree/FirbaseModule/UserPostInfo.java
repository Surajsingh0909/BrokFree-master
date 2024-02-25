package com.vm.brokfree.FirbaseModule;

public class UserPostInfo {
    String user_uid;
    String post_key;
    String user_phone_number;
   // Long search_value;


    public UserPostInfo() {
    }

    public UserPostInfo(String user_uid, String post_key, String user_phone_number) {

        this.user_uid = user_uid;
        this.post_key = post_key;
        this.user_phone_number = user_phone_number;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getPost_key() {
        return post_key;
    }

    public void setPost_key(String post_key) {
        this.post_key = post_key;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }
}
