package com.vm.brokfree.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vm.brokfree.menuFragment.Create;
import com.vm.brokfree.menuFragment.Favourite;
import com.vm.brokfree.menuFragment.profile;



public class fragmentAdapter extends FragmentStateAdapter {

    public fragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull

    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new Create();
            case 1:
                return new Favourite();
            case 2:
                return new profile();
            default:
                return new Create();
        }

    }

    @Override
    public int getItemCount()
    {
        return 3;
    }



}
