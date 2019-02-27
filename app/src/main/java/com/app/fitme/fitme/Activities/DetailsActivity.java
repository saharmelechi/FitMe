package com.app.fitme.fitme.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.fitme.fitme.Fragments.DetailsFragment;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        Bundle extras = getIntent().getExtras();
        detailsFragment.setDetails((Exerciser) extras.get("position"), extras.getBoolean("Edit", false));
    }
}
