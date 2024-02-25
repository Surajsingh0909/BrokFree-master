package com.vm.brokfree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;



import com.google.android.material.tabs.TabLayout;
import com.vm.brokfree.adapter.fragmentAdapter;

public class MainActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_main);
        setIds();
        setSupportActionBar(toolbar);

        setMenuAdapter();


    }



    private void setIds()
    {
        tabLayout = findViewById(R.id.Tab_Layout);
        viewPager2 =findViewById(R.id.View_Peger2);
        toolbar = findViewById(R.id.toolbar);

    }
    private void setMenuAdapter() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter fragmentAdapter = new fragmentAdapter(fragmentManager,getLifecycle());


//        tabLayout.addTab(tabLayout.newTab().setText("Create"));
//        tabLayout.addTab(tabLayout.newTab().setText("Favourite"));
//        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_create_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_favorite_border_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_person_24));
        viewPager2.setAdapter(fragmentAdapter);
        // adding new tab



//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_create_24);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_favorite_border_24);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_person_24);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
                // super.onPageSelected(position);
            }
        });
    }

}