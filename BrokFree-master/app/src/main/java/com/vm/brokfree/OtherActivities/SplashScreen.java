package com.vm.brokfree.OtherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vm.brokfree.Authentication.Login;
import com.vm.brokfree.MainActivity;
import com.vm.brokfree.R;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 1000;     // 1000 = 1 second
    String TAG = "SplashScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        disablingStatusBar();

        settingUpIds();
        updateUI();
    }

    private void updateUI() {
        new Handler().postDelayed(() -> {
            // Checking if the user is signed in (non-null) and update UI accordingly.
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null)
            {
                // Current user is logged in

                startActivity(new Intent(SplashScreen.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

            } else {
            // Proceeding to user verification
                Intent intent = new Intent(SplashScreen.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

    private void settingUpIds() {

    }

    private void disablingStatusBar() {
        // TODO: 25/01/22 status
        getWindow().setStatusBarColor(ContextCompat.getColor(SplashScreen.this,R.color.gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(SplashScreen.this,R.color.gray));
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}