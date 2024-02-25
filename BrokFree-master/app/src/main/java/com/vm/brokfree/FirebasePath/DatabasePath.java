package com.vm.brokfree.FirebasePath;

public class DatabasePath {
    String postsPath;
    String postsSpecificPath;
    String postsSpecificAddressPath;
    String postsSpecificCategoryPath;
    String postsSpecificPostDataPath;
    String postsSpecificUserInfoPath;
    String userFavouritePath;
    String userPostPath;
    String userInfoPath;



    public DatabasePath() {
    }

    public String postsPath() {
        postsPath = "posts/user_post";
        return postsPath;
    }

    public String postsSpecificPath(String postKey) {
        postsSpecificPath = "posts/user_post/"+ postKey;
        return "posts/user_post/"+ postKey;
    }

    public String postsSpecificAddressPath(String postKey) {
        postsSpecificAddressPath = "posts/user_post/"+ postKey+"/address";
        return postsPath;
    }

    public String postsSpecificCategoryPath(String postKey) {
        postsSpecificCategoryPath = "posts/user_post/"+ postKey+"/category";
        return postsSpecificCategoryPath;
    }

    public String postsSpecificPostDataPath(String postKey) {
        postsSpecificPostDataPath = "posts/user_post/"+ postKey+"/post_data";
        return postsSpecificPostDataPath;
    }

    public String postsSpecificUserInfoPath(String postKey) {
        postsSpecificUserInfoPath = "posts/user_post/"+ postKey+"/user_post_info";
        return postsSpecificUserInfoPath;
    }

    public String userFavouritePath(String userUid) {
        userFavouritePath = "user/"+ userUid+"/user_favourite_post";
        return userFavouritePath;
    }

    public String userPostPath(String userUid) {
        userPostPath = "user/"+ userUid+"/user_post";
        return userPostPath;
    }

    public String userInfoPath(String userUid) {
        userInfoPath = "user/"+ userUid+"/user_info";
        return userInfoPath;
    }
}
