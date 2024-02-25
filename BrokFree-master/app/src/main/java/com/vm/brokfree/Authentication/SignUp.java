package com.vm.brokfree.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vm.brokfree.R;
import com.vm.brokfree.R;

public class SignUp extends AppCompatActivity {

    TextInputEditText firstName,lastName,userPhoneNumber;
    MaterialButton signUp;
    TextView jumpToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        settingUpId();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    getVerify();


            }
        });
        jumpToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private void goToLogin() {
        Intent intent = new Intent(SignUp.this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void getVerify() {
        // TODO: 23/01/22 virify null
        Intent intent = new Intent(SignUp.this,SignUPOtp.class);
        intent.putExtra("FIRST_NAME",firstName.getEditableText().toString().trim());
        intent.putExtra("LAST_NAME",lastName.getEditableText().toString().trim());
        intent.putExtra("NEW_USER_NUMBER",userPhoneNumber.getEditableText().toString().trim());
        startActivity(intent);


    }

    private void settingUpId() {
        firstName = findViewById( R.id.user_firstname);
        lastName = findViewById( R.id.user_lastname);
        userPhoneNumber = findViewById( R.id.new_user_phone_number);
        signUp = findViewById( R.id.sign_up_btn);
        jumpToLogin = findViewById( R.id.login);
    }
}