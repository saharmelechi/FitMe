package com.app.fitme.fitme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class AchievementsActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 4566;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO: Copy the log in from the chatActivity from the example projects, throw away the login activity


        //need to implement create new exercisers and call the trainer adapter
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.default_avatar);
        Bitmap exercise = BitmapFactory.decodeResource(getResources(), R.drawable.pullup);


        Exerciser defaultExercise = new Exerciser("default", 22f, avatar, exercise);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Exerciser> exercisers = new ArrayList<Exerciser>();
        exercisers.add(defaultExercise);

        ExerciserAdapter exerciserAdapter = new ExerciserAdapter(exercisers);

        RecyclerView exerciseHolder = findViewById(R.id._dynamic_exercises);
        exerciseHolder.setAdapter(exerciserAdapter);
        exerciseHolder.setLayoutManager(new StaggeredGridLayoutManager(1 , LinearLayoutManager.VERTICAL));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

}
