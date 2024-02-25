package com.vm.brokfree.post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vm.brokfree.R;

public class CreatePostPageTwo extends AppCompatActivity {

    TextInputEditText pinCode, cityName, landmark, locality, apartmentName, roomNumber;
    MaterialButton nextButton;


    String selectedCategory ;
    String selectedCategoryType ;
    String selectedSubCategoryType ;
    String pinCodeText ;
    String cityNameText ;
    String landmarkText ;
    String localityText ;
    String apartmentNameText ;
    String roomNumberText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_page_two);
        settingUpId();
        initialTask();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNextButton();
            }
        });


    }


    private void settingUpId() {
        nextButton = findViewById(R.id.next_page_tow);
        pinCode = findViewById(R.id.pin_code);
        cityName = findViewById(R.id.city_name);
        landmark = findViewById(R.id.landmark);
        locality = findViewById(R.id.locality);
        apartmentName = findViewById(R.id.apartment_name);
        roomNumber = findViewById(R.id.room_number);

    }

    private void initialTask() {
        selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");
        selectedCategoryType = getIntent().getStringExtra("SELECTED_CATEGORY_TYPE");
        selectedSubCategoryType = getIntent().getStringExtra("SELECTED_SUB_CATEGORY_TYPE");
    }

    private void clickNextButton() {

        pinCodeText = pinCode.getEditableText().toString().trim();
        cityNameText = cityName.getEditableText().toString().trim();
        localityText = locality.getEditableText().toString().trim();
        landmarkText = landmark.getEditableText().toString().trim();
        apartmentNameText = apartmentName.getEditableText().toString().trim();
        roomNumberText = roomNumber.getEditableText().toString().trim();

        if (TextUtils.isEmpty(pinCode.getEditableText().toString().trim()) ) {
            pinCode.setError("invalid pin ode");
        }else if(TextUtils.isEmpty(cityName.getEditableText().toString().trim() )){
            pinCode.setError("invalid city name ode");
        }else if(TextUtils.isEmpty(landmark.getEditableText().toString().trim() )){
            pinCode.setError("invalid landmark");
        }else if(TextUtils.isEmpty(locality.getEditableText().toString().trim() )){
            pinCode.setError("invalid locality");
        }else if(TextUtils.isEmpty(apartmentName.getEditableText().toString().trim() )){
            pinCode.setError("invalid apartment name");
        }else if(TextUtils.isEmpty(roomNumber.getEditableText().toString().trim() )){
            pinCode.setError("invalid room number");
        }else {

            Toast.makeText(this, selectedCategory +"\n" +selectedCategoryType+"\n"+
                    selectedSubCategoryType+"\n"+ pinCodeText +"\n" +cityNameText+"\n"+
                    landmarkText +"\n" +localityText+"\n"+ apartmentNameText
                    +"\n" +roomNumberText+"\n", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreatePostPageTwo.this, CreatePostPostThree.class);
            intent.putExtra("SELECTED_CATEGORY", selectedCategory);
            intent.putExtra("SELECTED_CATEGORY_TYPE", selectedCategoryType);
            intent.putExtra("SELECTED_SUB_CATEGORY_TYPE", selectedSubCategoryType);
            intent.putExtra("PIN_CODE", pinCodeText);
            intent.putExtra("CITY_NAME", cityNameText);
            intent.putExtra("LANDMARK", landmarkText);
            intent.putExtra("LOCALITY", localityText);
            intent.putExtra("APARTMENT_NAME", apartmentNameText);
            intent.putExtra("ROOM_NUMBER", roomNumberText);

           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }




    }
}