package com.vm.brokfree.FirbaseModule.ModuleInterfase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vm.brokfree.FirbaseModule.CurrentUser;

import java.util.Objects;

public class CurrentUserInterface {
    public interface OnDataFetched {
        void onDetailsFetched(CurrentUser currentUserModel);
    }

    OnDataFetched dataFetched;
    String TAG = "currentUserInterFace";
    static String phone_no;
    static String first_name;
    static String last_name;
    static String uid;

    FirebaseAuth auth;
    FirebaseUser currentUser;

    public CurrentUserInterface() {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        phone_no = Objects.requireNonNull(currentUser).getPhoneNumber();
        uid = currentUser.getUid();

    }
    public void getCurrentUserInformation(OnDataFetched dataFetched) {
        this.dataFetched = dataFetched;

        FirebaseFirestore
                .getInstance()
                .collection("+918879920504")
                .document("user_info")
                .addSnapshotListener((value, error) -> {
                    if (value != null && value.exists() && error == null) {
                        first_name = value.getString("first_name");
                        last_name = value.getString("last_name");
                        CurrentUser currentUserModel = new CurrentUser(phone_no,first_name, last_name , uid);
                        dataFetched.onDetailsFetched(currentUserModel);

                    } else if (error != null) {
                        Log.d(TAG, "getCurrentUserInformation(): Error : " + error.getMessage());
                    }
                });
    }
}
