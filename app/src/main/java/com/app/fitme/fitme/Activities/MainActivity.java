package com.app.fitme.fitme.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.fitme.fitme.Fragments.DetailsFragment;
import com.app.fitme.fitme.Fragments.ExerciserListFragment;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.Models.FireBaseModel;
import com.app.fitme.fitme.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements ExerciserListFragment.SelectionListener {

    DetailsFragment detailsFragment;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                Exerciser exe = new Exerciser(userName);
                onItemSeleceted(exe, true);
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }else  {
            // Fake result to invoke connection callback
            onActivityResult(RC_SIGN_IN, RESULT_OK, this.getIntent());
        }

        // Add setting to the navigation bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);
        toolbar.setLogo(R.mipmap.ic_launcher);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_preferences);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(v.getContext(), SettingsActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onItemSeleceted(Exerciser exerciser,boolean EditMode) {
        detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);

        if (detailsFragment == null){
            Intent intent = new Intent();
            intent.setClass(this, DetailsActivity.class);
            intent.putExtra("position", exerciser);
            intent.putExtra("Edit", EditMode);
            startActivity(intent);
        }
        else {
            detailsFragment.setDetails(exerciser,EditMode);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {

            Snackbar.make(findViewById(android.R.id.content), FirebaseAuth.getInstance().getCurrentUser().getEmail() + " connected", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit().putString("user_name", FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).apply();

        }
    }


}
