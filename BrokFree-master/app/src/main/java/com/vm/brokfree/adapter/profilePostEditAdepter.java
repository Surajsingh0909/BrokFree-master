package com.vm.brokfree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vm.brokfree.R;

import java.util.ArrayList;
import java.util.List;

public class profilePostEditAdepter extends ArrayAdapter<String> {
    Context context;
//    String location[];
//    String address[];
//    int homeWorkImage[];
//    int createImage[];
    List<String> location = new ArrayList<>();
    List<String> address = new ArrayList<>();
    List<Integer> homeWorkImage = new ArrayList<>();
    List<Integer> createImage = new ArrayList<>();



    public profilePostEditAdepter(Context context, List<String> location, List<Integer> homeWorkIamge, List<Integer> createImage , List<String> address)
    {

        super(context,R.layout.adapter_post_edit_layout, R.id.Location_post_edit,location);
        this.context = context;
        this.homeWorkImage = homeWorkIamge;
        this.createImage = createImage;
        this.location = location;
        this.address = address;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_post_edit_layout,parent,false);
        ImageView HomeWorkImageview = (ImageView)view.findViewById(R.id.Home_work_post_edit);
        ImageView CreateImageView = (ImageView)view.findViewById(R.id.Edit_post_edit);
        TextView LocationTextView = (TextView) view.findViewById(R.id.Location_post_edit);
        TextView AddresTextView = (TextView) view.findViewById(R.id.Addess_post_edit);
        HomeWorkImageview.setImageResource(R.drawable.ic_outline_home_work_24);
        CreateImageView.setImageResource(R.drawable.ic_baseline_create_24);
        LocationTextView.setText(location.get(position));
        AddresTextView.setText(address.get(position));
         // onclick

        CreateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "update the data", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
