package com.vm.brokfree.menuFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vm.brokfree.FirbaseModule.CurrentUser;
import com.vm.brokfree.FirbaseModule.ModuleInterfase.CurrentUserInterface;
import com.vm.brokfree.FirebasePath.DatabasePath;
import com.vm.brokfree.R;
import com.vm.brokfree.adapter.PostAdapter;
import com.vm.brokfree.adapter.favouritePostAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Favourite extends Fragment {

    ListView listView;

    String currentUserId;
    String currentUserNumber;

    favouritePostAdapter adapter;

    List<String> favouritePostKeyList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userFavouriteReference ;
    ValueEventListener favouriteValueEventListener;
    Query query;
    DatabasePath path = new DatabasePath();

    Random random = new Random();
    Long searchFlag;

    CurrentUserInterface currentUserInterface = new CurrentUserInterface();
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        listView = view.findViewById(R.id.Favourite_List_VIew);
        initialTask();
        adddata();// to add the data in tha array
        setAdapter();// set adepter and show the data
        return view;


        // Inflate the layout for this fragment

    }
    private void initialTask() {
//        currentUserInterface.getCurrentUserInformation(new CurrentUserInterface.OnDataFetched() {
//            @Override
//            public void onDetailsFetched(CurrentUser currentUserModel) {
//                currentUserId = currentUserModel.getUuid();
//                currentUserNumber = currentUserModel.getPhone_no();
//            }
//        });
        currentUserId = auth.getCurrentUser().getUid();
        currentUserNumber = auth.getCurrentUser().getPhoneNumber();
        searchFlag = (long) random.nextInt(5);



    }
    private void adddata() {
       clearList();
        userFavouriteReference = database.getReference(path.userFavouritePath(currentUserId));
        favouriteValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        favouritePostKeyList.add(data.child("post_key").getValue(String.class));
                       // favouritePostKeyList.add(data.getKey());

                        adapter.notifyDataSetChanged();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
      userFavouriteReference.addListenerForSingleValueEvent(favouriteValueEventListener);
    }

    private void clearList() {
        favouritePostKeyList.clear();
    }

    private void setAdapter() {
        adapter = new favouritePostAdapter(getContext(),favouritePostKeyList,
                currentUserNumber);
        listView.setAdapter(adapter);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // This is important to remove to avoid further crashes
        if ( favouriteValueEventListener!= null) {
            userFavouriteReference.removeEventListener(favouriteValueEventListener);
        }


    }
}