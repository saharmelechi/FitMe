package com.app.fitme.fitme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.fitme.fitme.Fragments.DetailsFragment;
import com.app.fitme.fitme.Fragments.ExerciserListFragment;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.R;


public class MainActivity extends AppCompatActivity implements ExerciserListFragment.SelectionListener {

    DetailsFragment detailsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onItemSeleceted(Exerciser exerciser) {

        if (detailsFragment == null){
            Intent intent = new Intent();
            intent.setClass(this, DetailsActivity.class);
            intent.putExtra("position", exerciser);
            startActivity(intent);
        }
        else {
            detailsFragment.setDetails(exerciser);
        }

    }

}
