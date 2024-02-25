package com.vm.brokfree.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vm.brokfree.FirbaseModule.AddressInfo;
import com.vm.brokfree.FirbaseModule.CategoryInformation;
import com.vm.brokfree.FirbaseModule.PostDataInformation;
import com.vm.brokfree.FirbaseModule.UserPostInfo;
import com.vm.brokfree.FirebasePath.DatabasePath;
import com.vm.brokfree.R;
import com.vm.brokfree.OtherActivities.SellerInfo;

import java.util.ArrayList;
import java.util.List;

public class favouritePostAdapter extends ArrayAdapter<String> {
    Context context ;

    View view;
    LayoutInflater layoutInflater;

    String cityName;
    String locality ;
    String address ;
    String postUserNumber ;
    String category ;
    String categoryType ;
    String categorySubType ;
    String postUserUid;
    String currentUserUid;

    List<String> favouritePostKeyList ;


    MaterialTextView cityNameTextView;
    MaterialTextView localityTextview;
    MaterialTextView sellerMobileNumberTextview;
    MaterialTextView addressTextview ;
    MaterialTextView categoryTextView ;
    MaterialTextView categoryTypeTextView ;
    MaterialTextView categorySubTypeTextView ;
    ImageView postImage ;
    ImageView postFavouriteImage ;
    MaterialButton learnMoreButton ;
    MaterialButton sellerInfoButton ;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference ;
    ValueEventListener favouriteValueEventListener;

    DatabasePath path = new DatabasePath();
    AddressInfo addressInfo = new AddressInfo();
    CategoryInformation categoryInformation = new CategoryInformation();
    UserPostInfo userPostInfo = new UserPostInfo();
    PostDataInformation postDataInformation = new PostDataInformation();

    public favouritePostAdapter(Context context,  List<String> mFavouritePostKeyList,String mCurrentUserUid)
    {
        super(context, R.layout.adapter_favourite_post_layout,R.id.favourite_post_locality,mFavouritePostKeyList);
        this.context = context;
        this.favouritePostKeyList = mFavouritePostKeyList;
        this.currentUserUid = mCurrentUserUid;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         view = layoutInflater.inflate(R.layout.adapter_favourite_post_layout,parent,false);

        sttingUpIdes(view);
        satData(position);

        learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "learnMoreClick", Toast.LENGTH_LONG).show();
            }
        });
        sellerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sellerInfoButtonClick", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), SellerInfo.class);
                getContext().startActivity(intent);

            }
        });


        return view;
    }

    private void satData(int position) {
        reference =firebaseDatabase.getReference(path.postsSpecificPath(favouritePostKeyList.get(position)));
        favouriteValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    categoryInformation = snapshot.child("category").getValue(CategoryInformation.class);
                    addressInfo = snapshot.child("address").getValue(AddressInfo.class);
                    postDataInformation = snapshot.child("post_data").getValue(PostDataInformation.class);
                    userPostInfo = snapshot.child("user_post_info").getValue(UserPostInfo.class);


                /*    locality = snapshot.child("address/locality").getValue(String.class);
                    cityName = snapshot.child("address/city_name").getValue(String.class);
                    address = snapshot.child("address/room_number").getValue(String.class) + " " +
                            snapshot.child("address/apartment_name").getValue(String.class) + " " +
                            snapshot.child("address/landmark").getValue(String.class) + " " +
                            snapshot.child("address/locality").getValue(String.class) + " " +
                            snapshot.child("address/city_name").getValue(String.class) + " " +
                            snapshot.child("address/pin_code").getValue(String.class);
                    postUserNumber = snapshot.child("user_post_info/user_phone_number").getValue(String.class);
                    postUserUid = snapshot.child("user_post_info/user_uid").getValue(String.class);
           category = snapshot.child("category/category").getValue(String.class);
                    categoryType = snapshot.child("category/category_type").getValue(String.class);
                    categorySubType = snapshot.child("category/sub_category_type").getValue(String.class); */
                    locality = addressInfo.getLocality();
                    cityName = addressInfo.getCity_name();
                    address = addressInfo.getRoom_number() + " " +
                            addressInfo.getApartment_name() + " " +
                            addressInfo.getLandmark() + " " +
                            addressInfo.getLocality() + " " +
                            addressInfo.getCity_name() + " " +
                            addressInfo.getPin_code();
                    postUserNumber = userPostInfo.getUser_phone_number();
                    postUserUid = userPostInfo.getUser_uid();
                    category = categoryInformation.getCategory().trim();
                    categoryType = categoryInformation.getCategory_type().trim();
                    categorySubType = categoryInformation.getSub_category_type().trim();
                    // image view on fevourit


                    cityNameTextView.setText(cityName);
                    localityTextview.setText(locality);
                    addressTextview.setText(address);
                    sellerMobileNumberTextview.setText(postUserNumber);
                    categoryTextView.setText(category);
                    categoryTypeTextView.setText(categoryType);
                    categorySubTypeTextView.setText(categorySubType);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addListenerForSingleValueEvent(favouriteValueEventListener);

    }

    private void sttingUpIdes(View view) {
        localityTextview =  view.findViewById(R.id.favourite_post_locality);
        cityNameTextView =  view.findViewById(R.id.favourite_post_city_name);
        sellerMobileNumberTextview =  view.findViewById(R.id.favourite_post_user_number);
        addressTextview =   view.findViewById(R.id.favourite_post_address);
        categoryTextView =  view.findViewById(R.id.favourite_post_category);
        categoryTypeTextView =   view.findViewById(R.id.favourite_post_category_type);
        categorySubTypeTextView =   view.findViewById(R.id.favourite_post_category_sub_type);
        postImage =  view.findViewById(R.id.favourite_post_image);
        postFavouriteImage =   view.findViewById(R.id.favourite_post_favourite_icon);
        learnMoreButton =  view.findViewById(R.id.favourite_post_learn_more);
        sellerInfoButton =  view.findViewById(R.id.favourite_post_user_info);


    }
}
