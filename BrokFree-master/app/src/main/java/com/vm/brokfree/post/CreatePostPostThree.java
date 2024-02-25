package com.vm.brokfree.post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vm.brokfree.FirbaseModule.AddressInfo;
import com.vm.brokfree.FirbaseModule.CategoryInformation;
import com.vm.brokfree.FirbaseModule.CurrentUser;
import com.vm.brokfree.FirbaseModule.ModuleInterfase.CurrentUserInterface;
import com.vm.brokfree.FirbaseModule.PostDataInformation;
import com.vm.brokfree.FirbaseModule.SearchIndex;
import com.vm.brokfree.FirbaseModule.UserPostInfo;
import com.vm.brokfree.MainActivity;
import com.vm.brokfree.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatePostPostThree extends AppCompatActivity {
    TextInputEditText noOfBalconiesET, noOfBedroomsET, noOfBathroomsET, areaET, openParkingET, coverParkingET;
    ChipGroup furnishedGroup, otherRoomsGroup;
    Button saveButton;

    String selectedCategory;
    String selectedCategoryType;
    String selectedSubCategoryType;
    String pinCodeText;
    String cityNameText;
    String landmarkText;
    String localityText;
    String apartmentNameText;
    String roomNumberText;

    String noOfBalconies;
    String noOfBedrooms;
    String noOfBathroom;
    String area;
    String openParking;
    String coverParking;
    String furnishedType ;
    List<String> otherRoom = new ArrayList<>();
    long searchValue;

    String userUid;
    String postKey;
    String userPhoneNumber;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference addPostUserReference;
    DatabaseReference addPostsReference;
    CurrentUserInterface currentUserInterface = new CurrentUserInterface();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    Random randomNum = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_post_three);

        settingUpId();
        initialTask();
        saveButton.setOnClickListener(v -> savePost());

    }

    private void settingUpId() {
        currentUserInterface.getCurrentUserInformation(new CurrentUserInterface.OnDataFetched() {
            @Override
            public void onDetailsFetched(CurrentUser currentUserModel) {
                Toast.makeText(getApplication(), "user" + currentUserModel, Toast.LENGTH_SHORT).show();
            }
        });
        noOfBalconiesET = findViewById(R.id.no_of_balconies);
        noOfBathroomsET = findViewById(R.id.no_of_bathrooms);
        noOfBedroomsET = findViewById(R.id.no_of_bedrooms);
        areaET = findViewById(R.id.area_detail);
        openParkingET = findViewById(R.id.open_parking);
        coverParkingET = findViewById(R.id.cover_parking);
        saveButton = findViewById(R.id.save_post);

        furnishedGroup  = findViewById(R.id.furnished_group);
        otherRoomsGroup  = findViewById(R.id.other_room_group);


    }


    private void initialTask() {
        selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");
        selectedCategoryType = getIntent().getStringExtra("SELECTED_CATEGORY_TYPE");
        selectedSubCategoryType = getIntent().getStringExtra("SELECTED_SUB_CATEGORY_TYPE");
        pinCodeText = getIntent().getStringExtra("PIN_CODE");
        cityNameText = getIntent().getStringExtra("CITY_NAME");
        landmarkText = getIntent().getStringExtra("LANDMARK");
        selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");
        localityText = getIntent().getStringExtra("LOCALITY");
        roomNumberText = getIntent().getStringExtra("ROOM_NUMBER");
        apartmentNameText = getIntent().getStringExtra("APARTMENT_NAME");
        userUid = currentUser.getUid();
        userPhoneNumber = currentUser.getPhoneNumber();
        searchValue = (long) randomNum.nextInt(5);




    }

    private void savePost() {
        if (TextUtils.isEmpty(noOfBalconiesET.getEditableText().toString().trim())){
            noOfBalconiesET.setError("invalid data");
        }else if (TextUtils.isEmpty(noOfBedroomsET.getEditableText().toString().trim())){
            noOfBedroomsET.setError("invalid data");
        }else if (TextUtils.isEmpty(noOfBathroomsET.getEditableText().toString().trim())){
            noOfBathroomsET.setError("invalid data");
        }else if (TextUtils.isEmpty(areaET.getEditableText().toString().trim())){
            areaET.setError("invalid data");
        }else if (TextUtils.isEmpty(openParkingET.getEditableText().toString().trim())){
            openParkingET.setError("invalid data");
        }else if (TextUtils.isEmpty(coverParkingET.getEditableText().toString().trim())){
            coverParkingET.setError("invalid data");
        }
        else {
            noOfBalconies = noOfBalconiesET.getEditableText().toString().trim();
            noOfBedrooms = noOfBedroomsET.getEditableText().toString().trim();
            noOfBathroom = noOfBathroomsET.getEditableText().toString().trim();
            area = areaET.getEditableText().toString().trim();
            openParking = openParkingET.getEditableText().toString().trim();
            coverParking = coverParkingET.getEditableText().toString().trim();

            for (Integer id: furnishedGroup.getCheckedChipIds()) {
                Chip chip = furnishedGroup.findViewById(id);
                furnishedType = (chip.getText().toString());
            }
            for (Integer id: otherRoomsGroup.getCheckedChipIds()) {
                Chip chip = otherRoomsGroup.findViewById(id);
                otherRoom.add(chip.getText().toString());
            }
            addPostUserReference = database.getReference("user/"+userUid+"/user_post").push();
            postKey = addPostUserReference.getKey();
            addPostUserReference.child("post_key").setValue(postKey);

            addPostsReference = database.getReference("posts/user_post/"+postKey);

            CategoryInformation categoryInformation = new CategoryInformation(selectedCategory,selectedCategoryType,selectedSubCategoryType);
            AddressInfo addressInfo = new AddressInfo(pinCodeText,cityNameText,landmarkText,localityText,apartmentNameText,roomNumberText);
            PostDataInformation postDataInformation = new PostDataInformation(noOfBalconies,noOfBedrooms,noOfBathroom,area,openParking,coverParking,furnishedType,otherRoom);
            UserPostInfo userPostInfo = new UserPostInfo(userUid,postKey,userPhoneNumber);
            SearchIndex searchIndex = new SearchIndex(searchValue);
            addPostsReference.setValue(searchIndex);
            addPostsReference.child("address").setValue(addressInfo);
            addPostsReference.child("category").setValue(categoryInformation);
            addPostsReference.child("post_data").setValue(postDataInformation);
            addPostsReference.child("user_post_info").setValue(userPostInfo);

            Toast.makeText( CreatePostPostThree.this, "data save", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(CreatePostPostThree.this, MainActivity.class)
//                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


        }


    }

}