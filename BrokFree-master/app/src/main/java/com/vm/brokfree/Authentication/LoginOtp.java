package com.vm.brokfree.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.vm.brokfree.MainActivity;
import com.vm.brokfree.R;

import java.util.concurrent.TimeUnit;

public class LoginOtp extends AppCompatActivity {
    EditText otp_digit_1,otp_digit_2,otp_digit_3,otp_digit_4,otp_digit_5,otp_digit_6;
    Button verify,resenOtp;
    String TAG = "LoginUPOtp";
    TextView loginOtpTimer;
    String userPhoneNumber;
    String otpFromFirebase;
    String enteredOtp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        settingUpIds();
        initialTask();

        sendingOtp();
        editTextActionNext();
        goBackToPreviousEditText();
        startCountDownTimer();
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPhoneNumber();

            }
        });
    }

    private void verifyPhoneNumber() {
        // TODO: 24/01/22  validetion
        enteredOtp = otp_digit_1.getEditableText().toString().trim()+
                otp_digit_2.getEditableText().toString().trim()+
                otp_digit_3.getEditableText().toString().trim()+
                otp_digit_4.getEditableText().toString().trim()+
                otp_digit_5.getEditableText().toString().trim()+
                otp_digit_6.getEditableText().toString().trim();

        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otpFromFirebase, enteredOtp);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }


    private void initialTask() {
        userPhoneNumber = getIntent().getStringExtra("USER_NUMBER");
        askSmsPermission();
    }

    private void settingUpIds() {
        otp_digit_1 = findViewById(R.id.login_otp_digit_1);
        otp_digit_2 = findViewById(R.id.login_otp_digit_2);
        otp_digit_3 = findViewById(R.id.login_otp_digit_3);
        otp_digit_4 = findViewById(R.id.login_otp_digit_4);
        otp_digit_5 = findViewById(R.id.login_otp_digit_5);
        otp_digit_6 = findViewById(R.id.login_otp_digit_6);
        verify = findViewById(R.id.login_verify_otp);
        loginOtpTimer = findViewById(R.id.login_otp_timer);
        resenOtp = findViewById(R.id.login_resend_otp_btn);
    }
    private void sendingOtp() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(userPhoneNumber)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                if(ContextCompat.checkSelfPermission(LoginOtp.this,
                                        Manifest.permission.READ_SMS)
                                        == PackageManager.PERMISSION_GRANTED )
                                    signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.d(TAG, "onVerificationFailed: otp not arriveng ");
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                otpFromFirebase = s;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                      
                            startActivity(new Intent(LoginOtp.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid tham
                            }
                        }
                    }
                });
    }

    /*   @Override
       protected void onDestroy() {
           super.onDestroy();
   
       }*/
    private void askSmsPermission() {
        ActivityCompat.requestPermissions(LoginOtp.this, new String[]{Manifest.permission.READ_SMS}, 1);
    }

    private void editTextActionNext() {
        otp_digit_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_digit_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_digit_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_digit_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_digit_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_digit_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_digit_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_digit_5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp_digit_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_digit_6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void startCountDownTimer() {
        new CountDownTimer(120000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                loginOtpTimer.setText("You will receive an OTP on " + userPhoneNumber + "\n in " + (millisUntilFinished / 1000) + " second/s.");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                loginOtpTimer.setText("Please click on 'Resend' ");
                loginOtpTimer.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void goBackToPreviousEditText() {
        otp_digit_6.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                otp_digit_5.requestFocus();
            }
            return false;
        });
        otp_digit_5.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                otp_digit_4.requestFocus();
            }
            return false;
        });
        otp_digit_4.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                otp_digit_3.requestFocus();
            }
            return false;
        });
        otp_digit_3.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                otp_digit_2.requestFocus();
            }
            return false;
        });
        otp_digit_2.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                otp_digit_1.requestFocus();
            }
            return false;
        });
    }
}