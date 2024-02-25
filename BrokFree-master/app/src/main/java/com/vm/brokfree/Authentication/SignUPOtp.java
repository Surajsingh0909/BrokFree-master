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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.vm.brokfree.FirbaseModule.SetUser;
import com.vm.brokfree.MainActivity;
import com.vm.brokfree.R;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignUPOtp extends AppCompatActivity {
    String firstName,lastName,userPhoneNumber, enteredOtp;
    EditText otp_digit_1,otp_digit_2,otp_digit_3,otp_digit_4,otp_digit_5,otp_digit_6;
    Button verify,resenOtp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    HashMap<String, Object> personalInformation = new HashMap<>();
    String otpFromFirebase;
    String userUid;
    TextView regOtpTimer;
    DatabaseReference databaseReference ;
    FirebaseFirestore databasePath = FirebaseFirestore.getInstance();

    String TAG = "SignUPOtp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upotp);
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
//bol accept nhi kela nahi
            }
        });

    }

    private void verifyPhoneNumber() {
        // TODO: 24/01/22
        enteredOtp = otp_digit_1.getEditableText().toString().trim()+
                otp_digit_2.getEditableText().toString().trim()+
                otp_digit_3.getEditableText().toString().trim()+
                otp_digit_4.getEditableText().toString().trim()+
                otp_digit_5.getEditableText().toString().trim()+
                otp_digit_6.getEditableText().toString().trim();

        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otpFromFirebase, enteredOtp);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }


    private void settingUpIds() {
        otp_digit_1 = findViewById(R.id.otp_digit_1);
        otp_digit_2 = findViewById(R.id.otp_digit_2);
        otp_digit_3 = findViewById(R.id.otp_digit_3);
        otp_digit_4 = findViewById(R.id.otp_digit_4);
        otp_digit_5 = findViewById(R.id.otp_digit_5);
        otp_digit_6 = findViewById(R.id.otp_digit_6);
        verify = findViewById(R.id.register_verify_otp);
        regOtpTimer = findViewById(R.id.register_otp_timer);
        resenOtp = findViewById(R.id.register_resend_otp_btn);

    }

    private void initialTask() {

        firstName = getIntent().getStringExtra("FIRST_NAME");
        lastName = getIntent().getStringExtra("LAST_NAME");
        userPhoneNumber = getIntent().getStringExtra("NEW_USER_NUMBER");
        askSmsPermission();
        Toast.makeText(SignUPOtp.this, userPhoneNumber, Toast.LENGTH_SHORT).show();

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
                         if(ContextCompat.checkSelfPermission(SignUPOtp.this,
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

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            userUid = mAuth.getCurrentUser().getUid();
                            SetUser setUser = new SetUser(firstName,lastName,userPhoneNumber,userUid);
//                            personalInformation.put("first_name", firstName);
//                            personalInformation.put("last_name", lastName);
//                            personalInformation.put("phone_number", userPhoneNumber);
//                            personalInformation.put("UID", userUid);
                             databaseReference = FirebaseDatabase.getInstance().getReference("user/"+userUid+"/user_info");
                            databaseReference.setValue(setUser);
                            databasePath.collection(userPhoneNumber).document("user_info")
                                    .set(setUser);

                            startActivity(new Intent(SignUPOtp.this, Login.class)
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
     ActivityCompat.requestPermissions(SignUPOtp.this, new String[]{Manifest.permission.READ_SMS}, 1);
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
                regOtpTimer.setText("You will receive an OTP on " + userPhoneNumber + "\n in " + (millisUntilFinished / 1000) + " second/s.");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                regOtpTimer.setText("Please click on 'Resend' ");
                regOtpTimer.setVisibility(View.VISIBLE);
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