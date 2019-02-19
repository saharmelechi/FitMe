package com.app.fitme.fitme.Activities;

import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.fitme.fitme.Fragments.DetailsFragment;
import com.app.fitme.fitme.Fragments.ExerciserListFragment;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.Models.FireBaseModel;
import com.app.fitme.fitme.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements ExerciserListFragment.SelectionListener {

    DetailsFragment detailsFragment;
    private static final int RC_SIGN_IN = 1;
    private static final int PICK_IMAGE = 333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable persistence mode to use in the application later
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding pog exercise", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select file"), PICK_IMAGE);

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
            onActivityResult(RC_SIGN_IN, RESULT_OK, this.getIntent());
        }
    }

    @Override
    public void onItemSeleceted(Exerciser exerciser) {
        detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {


            Exerciser m = new Exerciser("",data.getData().toString(), "Intense exercise", "long content here", 98998089891L );
            FireBaseModel.instance.upload(m);

//            fileToUpload = data.getData();
//
//                Glide.with(getContext())
//                        .load(fileToUpload)
//                        .into(imgNew);

        } else if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {

            Snackbar.make(findViewById(android.R.id.content), FirebaseAuth.getInstance().getCurrentUser().getEmail() + " connected", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }


}
