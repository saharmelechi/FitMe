package com.app.fitme.fitme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        ListView exerciseHolder = findViewById(R.id._dynamic_exercises);
        exerciseHolder.setAdapter(exerciserAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    Intent intent = new Intent(AchievementsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

}
