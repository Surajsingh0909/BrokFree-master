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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.vm.brokfree.FirbaseModule.AddressInfo;
import com.vm.brokfree.FirbaseModule.CategoryInformation;
import com.vm.brokfree.FirbaseModule.PostDataInformation;
import com.vm.brokfree.FirbaseModule.UserPostInfo;
import com.vm.brokfree.R;
import com.vm.brokfree.OtherActivities.SellerInfo;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter<String> {
    View view;
    LayoutInflater layoutInflater;

    Context context;
    String cityName;
    String locality ;
    String address ;
    String postUserNumber ;
    String category ;
    String categoryType ;
    String categorySubType ;
    String postUserUid;

    List<DataSnapshot> dataSnapshotList ;
    List<String> tempList ;

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

    AddressInfo addressInfo = new AddressInfo();
    CategoryInformation categoryInformation = new CategoryInformation();
    UserPostInfo userPostInfo = new UserPostInfo();
    PostDataInformation postDataInformation = new PostDataInformation();
    public PostAdapter(Context context, List<DataSnapshot> mDataSnapshotList, List<String> mTempList) {
        super(context, R.layout.adapter_post_layout, R.id.post_locality, mTempList);
        this.context = context;
        this.dataSnapshotList = mDataSnapshotList;
        this.tempList = mTempList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         view = layoutInflater.inflate(R.layout.adapter_post_layout, parent, false);


         sttingUpIdes(view);

         setData(position);


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

    private void setData(int position) {
        DataSnapshot snapshot = dataSnapshotList.get(position);

        categoryInformation = snapshot.child("category").getValue(CategoryInformation.class);
        addressInfo = snapshot.child("address").getValue(AddressInfo.class);
        postDataInformation = snapshot.child("post_data").getValue(PostDataInformation.class);
        userPostInfo = snapshot.child("user_post_info").getValue(UserPostInfo.class);

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
        // postImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        // postImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        categoryTextView.setText(category);
        categoryTypeTextView.setText(categoryType);
        categorySubTypeTextView.setText(categorySubType);

    }

    private void sttingUpIdes(View view) {
        localityTextview =  view.findViewById(R.id.post_locality);
        cityNameTextView =  view.findViewById(R.id.post_city_name);
        sellerMobileNumberTextview =  view.findViewById(R.id.post_user_number);
        addressTextview =   view.findViewById(R.id.post_address);
        categoryTextView =  view.findViewById(R.id.post_category);
        categoryTypeTextView =   view.findViewById(R.id.post_category_type);
        categorySubTypeTextView =   view.findViewById(R.id.post_category_sub_type);
        postImage =  view.findViewById(R.id.post_image);
        postFavouriteImage =   view.findViewById(R.id.post_favourite_icon);
        learnMoreButton =  view.findViewById(R.id.post_learn_more);
        sellerInfoButton =  view.findViewById(R.id.post_user_info);

    }
  /*public PostAdapter(Context context, ArrayList<postPattern> postPattern)
        {
            super(context, R.layout.adapter_post_layout, postPattern);
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        postPattern post = new postPattern("locstion","mobilenum","addd");
        if(convertView != null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_post_layout,parent,false);
        }

       // ImageView Favourite = convertView.findViewById(R.id.Favourite);
        TextView Location =  convertView.findViewById(R.id.Location);
        TextView SellerNumber =  convertView.findViewById(R.id.Seller_mobile_number);
        TextView Address =  convertView.findViewById(R.id.Address);
       // Button LearnMore = convertView.findViewById(R.id.Learn_more);
       // Button SellerInfo = convertView.findViewById(R.id.seller_info);


        Location.setText(post.getLocation());
        SellerNumber.setText(post.getUserMobileNumber());
        Address.setText(post.getAddress());
        return super.getView(position, convertView, parent);
    }*/
}

