package com.app.fitme.fitme.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.fitme.fitme.Fragments.IntroFragment;
import com.app.fitme.fitme.Models.Data;
import com.app.fitme.fitme.R;


public class IntroAdapter extends FragmentPagerAdapter {

    Data[] data = { new Data("Do you feel like an Elephant?", R.drawable.intro1),
            new Data("Exercise with us", R.drawable.intro2),
            new Data("And be happy", R.drawable.intro3)};

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return IntroFragment.newInstance(data[position], position);
    }

    @Override
    public int getCount() {
        return 3;
    }

}