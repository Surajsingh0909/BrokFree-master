package com.vm.brokfree.menuFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vm.brokfree.FirebasePath.DatabasePath;
import com.vm.brokfree.R;
import com.vm.brokfree.adapter.PostAdapter;
import com.vm.brokfree.post.CreatePostPageOne;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Create extends Fragment {
    ListView listView;
    FloatingActionButton addFab;
    PostAdapter adapter;
    List<String> LocationList = new ArrayList<>();
    List<String> AddressList = new ArrayList<>();
    List<String> SellerMobileNumberList = new ArrayList<>();
    List<String> TypeOfPropertyList = new ArrayList<>();
    List<String> PropertyPatternList = new ArrayList<>();
    List<Integer> ImageOfFavouriteList = new ArrayList<>();

    List<DataSnapshot> dataSnapshotList = new ArrayList<>();
    List<String> tempList = new ArrayList<>();

    DatabasePath path = new DatabasePath();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference postsReference = database.getReference(path.postsPath());
    ValueEventListener postValueEventListener;
    Query query;

    Random random = new Random();
    Long searchFlag;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_manu, menu);
        //menu.findItem(R.id.searchIcon).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchIcon) {
            Toast.makeText(getContext(), "click search", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create, container, false);
        settingUpIds(view);

        initialTask();
        settingListViewAdapter();
        adddata();// to add the data in tha array

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CreatePostPageOne.class);
                getContext().startActivity(intent);


            }
        });
        return view;

    }

    private void initialTask() {
        searchFlag = (long) random.nextInt(5);
        Toast.makeText(getContext(), searchFlag + "hi", Toast.LENGTH_SHORT).show();

    }

    private void settingUpIds(View view) {
        listView = view.findViewById(R.id.Post_List_View);
        addFab = view.findViewById(R.id.create_fab);
    }


    private void adddata() {
        dataSnapshotList.clear();
        tempList.clear();

        postValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        dataSnapshotList.add(data);
                        tempList.add("null");
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        query = postsReference.orderByChild("search_value").equalTo(searchFlag);
        //    postsReference.orderByChild("search_value").equalTo(searchFlag)
        query.addListenerForSingleValueEvent(postValueEventListener);


    }


    private void settingListViewAdapter() {
        adapter = new PostAdapter(getContext(), dataSnapshotList, tempList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // This is important to remove to avoid further crashes
        if (postValueEventListener != null) {
            postsReference.removeEventListener(postValueEventListener);
        }


    }
}




