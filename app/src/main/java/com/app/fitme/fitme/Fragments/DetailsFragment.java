package com.app.fitme.fitme.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.Models.FireBaseModel;
import com.app.fitme.fitme.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;

import static android.app.Activity.RESULT_OK;


public class DetailsFragment extends Fragment {
    private static final int PICK_IMAGE = 333;

    EditText tvTitle;
    EditText tvContent;
    EditText  tvName;
    ImageView imgAvatar;
    ImageView imgExercise;
    TextView tvDate;
    FloatingActionButton btnEditSave;
    ImageButton btnStar;
    private String imgExerciseUri;


    public DetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exercise_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle = view.findViewById(R.id.tvTitle);
        tvContent =view.findViewById(R.id.tvContent);
        tvName =  view.findViewById(R.id.tvName);
        tvDate =  view.findViewById(R.id.tvDate);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        imgExercise = (ImageView) view.findViewById(R.id.exerciseImage);
        btnEditSave = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTitle.isEnabled()) {
                    save();
                } else {
                    SetEdit(true);
                }
//                Intent intent = new Intent();
//                intent.setClass(v.getContext(), BottomNavActivity.class);
//                startActivity(intent);
            }
        });

        view.setVisibility(View.INVISIBLE);
    }



    public void setDetails(Exerciser exerciser,boolean EditMode) {
        tvDate.setText(exerciser.formatDate());
        tvTitle.setText(exerciser.getSubject());
        tvContent.setText(exerciser.getContent());
        tvName.setText(exerciser.getName());


        setImgExercise(exerciser.getExeImage());

        SetEdit(EditMode);
        if (!getView().isShown())
            getView().setVisibility(View.VISIBLE);
    }

    private  void setImgExercise(String image){
        Glide.with(getContext()).load(image).into(imgExercise);
        imgExerciseUri = image;
    }

    public void SetEdit(boolean EditMode) {
        tvTitle.setEnabled(EditMode);
        tvContent.setEnabled(EditMode);
        tvName.setEnabled(false);


        if (EditMode) {
            btnEditSave.setImageResource(android.R.drawable.ic_menu_save);

            imgExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select file"), PICK_IMAGE);

                }
            });
        } else  {
            btnEditSave.setImageResource(R.drawable.ic_create_white_48dp);
            imgExercise.setOnClickListener(null);
        }
    }

    private void save() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            long time = sdf.parse(tvDate.getText().toString()).getTime();

            Exerciser exerciser  = new Exerciser(tvName.getText().toString(),
                    imgExerciseUri,
                    tvTitle.getText().toString(),
                    tvContent.getText().toString(),
                    time);

            // if it's already in the cloud we don't need to reupload the image
            if (exerciser.getExeImage().startsWith("http")) FireBaseModel.instance.addExercise(exerciser);
            // Else we need to upload the image
            else FireBaseModel.instance.upload(exerciser);

        }catch (Exception e)
        {
            Snackbar.make(getView(), "Exercise Failed to save", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        Snackbar.make(getView(), "Exercise Saved", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        SetEdit(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            setImgExercise(data.getData().toString());
        }
    }
}
