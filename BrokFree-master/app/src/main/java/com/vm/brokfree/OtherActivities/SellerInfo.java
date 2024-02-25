package com.vm.brokfree.OtherActivities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vm.brokfree.R;

import java.util.ArrayList;
import java.util.List;

public class SellerInfo extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    List<String> LocationList = new ArrayList<>();
    List<String> AddressList= new ArrayList<>();
    List<String> SellerMobileNumberList= new ArrayList<>();
    List<String> TypeOfPropertyList= new ArrayList<>();
    List<String> PropertyPatternList= new ArrayList<>();
    List<Integer> ImageOfFavouriteList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_info);
        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CreateIds();
        setDataList();
        setAdepter();
    }




    private void CreateIds() {
        listView = findViewById(R.id.list_view_seller_info);
        toolbar = findViewById(R.id.sellerInfoToolBar);

    }
    private void setDataList() {
        for (int i = 0; i <=9 ; i++)
        {
            LocationList.add("Location");
            AddressList.add("address");
            SellerMobileNumberList.add("1234567899");
            TypeOfPropertyList.add("Residential");
            PropertyPatternList.add("Shop");
            ImageOfFavouriteList.add( R.drawable.ic_baseline_favorite_border_24);

        }

    }
    private void setAdepter() {
        AdepterSellerInfoAnotherProperty adapter = new AdepterSellerInfoAnotherProperty(SellerInfo.this,LocationList,AddressList,SellerMobileNumberList,TypeOfPropertyList,PropertyPatternList,ImageOfFavouriteList);
        listView.setAdapter(adapter);
    }
    public class AdepterSellerInfoAnotherProperty extends ArrayAdapter<String>
    {

        Context context ;
        List<String> Location = new ArrayList<>();
        List<String> Address = new ArrayList<>();
        List<String> SellerMobileNumber = new ArrayList<>();
        List<String> TypeOfProperty = new ArrayList<>();
        List<String> PropertyPattern = new ArrayList<>();
        List<Integer> ImageOfFavvourite =  new ArrayList<>();
        public AdepterSellerInfoAnotherProperty(Context context, List<String> Location,List<String> Address,List<String> SellerMobileNumber,List<String> TypeOfProperty,  List<String> PropertyPattern, List<Integer> Favourite)
        {
            super(context,R.layout.adapter_seller_info_another_propertity_layout,R.id.Location_Seller_info,Location);
            this.context = context;
            this.Location = Location;
            this.Address = Address;
            this.SellerMobileNumber = SellerMobileNumber;
            this.ImageOfFavvourite = Favourite;
            this.TypeOfProperty = TypeOfProperty;
            this.PropertyPattern = PropertyPattern;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {

            LayoutInflater layoutInflater ;
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.adapter_seller_info_another_propertity_layout,parent,false);
            TextView LocationTextview = (TextView) view.findViewById(R.id.Location_Seller_info);
            TextView SellerMobileNumberTextview = (TextView) view.findViewById(R.id.Seller_mobile_number_Seller_info);
            TextView AddresTextview = (TextView) view.findViewById(R.id.Address_Seller_info);
            TextView TypeOfPorpertyTextView = (TextView) view.findViewById(R.id.Type_of_property_Seller_info);
            TextView PropertyPatternTextView = (TextView) view.findViewById(R.id.Property_Pattern_Seller_info);

            // image view on fevourit
            ImageView FavouriteImage = (ImageView) view.findViewById(R.id.Favourite_Seller_info);
            Button LearnMoreButton = (Button) view.findViewById(R.id.Learn_more_Seller_info);

            LocationTextview.setText(Location.get(position));
            AddresTextview.setText(Address.get(position));
            SellerMobileNumberTextview.setText(SellerMobileNumber.get(position));
            FavouriteImage.setImageResource(ImageOfFavvourite.get(position));
            TypeOfPorpertyTextView.setText(TypeOfProperty.get(position));
            PropertyPatternTextView.setText(PropertyPattern.get(position));
            LearnMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "learnMoreClick", Toast.LENGTH_LONG).show();
                }
            });
             return view;

        }
    }
}