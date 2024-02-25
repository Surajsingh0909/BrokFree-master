package com.vm.brokfree.menuFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.vm.brokfree.R;
import com.vm.brokfree.adapter.profilePostEditAdepter;

import java.util.ArrayList;
import java.util.List;


public class profile extends Fragment {


ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    List<String> LocationList = new ArrayList<>();
    List<String> AddressList = new ArrayList<>();
    List<Integer> HomeWorkList = new ArrayList<>();
    List<Integer> CreateList = new ArrayList<>();
//    String LocationList[] = new String[8];
//    String AddressList[] = new String[8];
//    int HomeWorkList[] = new int[8];
//    int CreateList[] = new int[8];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        listView = view.findViewById(R.id.List_view_Post_edit);

        adddata();// to add the data in tha array
        setDataInFragment();// set adepter and show the data
        return view;
    }

    private void setDataInFragment() {
        profilePostEditAdepter profilePostEditAdepter  = new profilePostEditAdepter(getContext(),LocationList,HomeWorkList,CreateList,AddressList);
        listView.setAdapter(profilePostEditAdepter);
    }

    private void adddata() {
        for (int i = 0; i <=7 ; i++) {
            LocationList.add("Location");
            AddressList.add("Address");
            HomeWorkList.add(R.drawable.ic_outline_home_work_24);
            CreateList.add( R.drawable.ic_baseline_create_24);
        }

    }
}