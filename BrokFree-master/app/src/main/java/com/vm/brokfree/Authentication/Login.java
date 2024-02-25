package com.vm.brokfree.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;



import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import com.vm.brokfree.R;

public class Login extends AppCompatActivity {


    TextInputEditText getUserPhoneNumber;
    MaterialButton login;
    TextView goToSignUp;


    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference userNodeReference;
    String userEnterNumber;
    final private String TAG = "Login";
    public boolean verificationFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settingUpIds();
        initialTask();
        login.setOnClickListener(v -> {
            toVerifyPhoneNumber();
        });
        goToSignUp.setOnClickListener(v -> goToSignUpPage());

    }
    private void settingUpIds() {
        getUserPhoneNumber = findViewById(R.id.user_phone_number);
        login = findViewById(R.id.login_btn);
        goToSignUp = findViewById(R.id.register);
    }
    private void initialTask() {


    }
    private void toVerifyPhoneNumber() {
        userEnterNumber = getUserPhoneNumber.getEditableText().toString().trim();
        userNodeReference = firebaseFirestore
                .collection(getUserPhoneNumber.getEditableText().toString().trim());
        Query query = userNodeReference
                .whereEqualTo("phone_number", getUserPhoneNumber.getEditableText().toString().trim());
//            firebaseFirestore
//                    .collection(getUserPhoneNumber.getEditableText().toString().trim())
//                    .whereEqualTo("phone_number",getUserPhoneNumber.getEditableText().toString().trim())
//                    .get()
        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (document.getId().equals("user_info") && document.get("phone_number").equals(userEnterNumber)) {
                                verificationFlag = false;
                                Log.d(TAG, "onComplete: query find");
                                //  Toast.makeText(getApplicationContext(), "you are user", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, LoginOtp.class);
                                intent.putExtra("USER_NUMBER", getUserPhoneNumber.getEditableText().toString().trim());
                                startActivity(intent);
                                break;
                            }
                        }
                        if (verificationFlag) {
                            Toast.makeText(getApplicationContext(), "not the user singUp", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onComplete: query not find");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onComplete: error occure query");
                    }
                });
    }
    private void goToSignUpPage() {
        Intent intent = new Intent(Login.this, SignUp.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }




}