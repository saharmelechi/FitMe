package com.app.fitme.fitme.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.app.fitme.fitme.Fragments.IntroFragment;
import com.app.fitme.fitme.Models.Data;
import com.app.fitme.fitme.R;


public class BottomNavActivity extends AppCompatActivity {

    final String TAG = "TAGF";

    int counter = 0;

    Data[] data = { new Data("Add Only", R.drawable.intro1),
            new Data("Add + back stack", R.drawable.intro2),
            new Data("Replace", R.drawable.intro3),
            new Data("Replace + back stack", R.drawable.intro1),
            new Data("Remove", R.drawable.intro2),
            new Data("Remove + back stack", R.drawable.intro3)};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            counter++;

            Log.d(TAG, "Counter: " + String.valueOf(counter));

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d(TAG, String.valueOf(fragmentManager.getBackStackEntryCount()) + " " + fragmentManager.getFragments().size());
                    transaction.add(R.id.fragmentsFrame, IntroFragment.newInstance(data[0], 0), "whatever");
                    transaction.commit();

                    return true;
                case R.id.navigation_dashboard:
                    Log.d(TAG, String.valueOf(fragmentManager.getBackStackEntryCount()) + " " + fragmentManager.getFragments().size());
                    transaction.add(R.id.fragmentsFrame, IntroFragment.newInstance(data[1], 0));
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;
                case R.id.navigation_notifications:
                    Log.d(TAG, String.valueOf(fragmentManager.getBackStackEntryCount()) + " " + fragmentManager.getFragments().size());
                    transaction.add(R.id.fragmentsFrame, IntroFragment.newInstance(data[2], 0), "whatever");
                    transaction.addToBackStack(null);
                    Fragment f = fragmentManager.findFragmentByTag("whatever");
                    if(f != null)
                        transaction.remove(f).commit();

                    return true;
                case R.id.navigation_Updates:
                    Log.d(TAG, String.valueOf(fragmentManager.getBackStackEntryCount()) + " " + fragmentManager.getFragments().size());
                    transaction.replace(R.id.fragmentsFrame, IntroFragment.newInstance(data[3], 0), "whatever");
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;
                case R.id.navigation_Remove:
                    Log.d(TAG, String.valueOf(fragmentManager.getBackStackEntryCount()) + " " + fragmentManager.getFragments().size());
                    Fragment fragment = fragmentManager.findFragmentByTag("whatever");
                    if(fragment != null)
                        transaction.remove(fragment).addToBackStack(null).commit();

                    return true;

            }



            return false;
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("counter", counter);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("counter");
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    public void onBackPressed() {

        Log.d(TAG, String.valueOf(getSupportFragmentManager().getBackStackEntryCount()) + " " + getSupportFragmentManager().getFragments().size());

        //getSupportFragmentManager().popBackStack();

        super.onBackPressed();


    }
}
