package com.app.fitme.fitme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.fitme.fitme.Fragments.DetailsFragment;
import com.app.fitme.fitme.Fragments.MailListFragment;
import com.app.fitme.fitme.Models.Mail;
import com.app.fitme.fitme.R;


public class MainActivity extends AppCompatActivity implements MailListFragment.SelectionListener {

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
    public void onItemSeleceted(Mail mail) {

        if (detailsFragment == null){
            Intent intent = new Intent();
            intent.setClass(this, DetailsActivity.class);
            intent.putExtra("position", mail);
            startActivity(intent);
        }
        else {
            detailsFragment.setDetails(mail);
        }

    }

}
