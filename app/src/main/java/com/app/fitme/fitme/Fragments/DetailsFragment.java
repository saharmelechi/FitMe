package com.app.fitme.fitme.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fitme.fitme.Activities.BottomNavActivity;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.R;

import java.util.Calendar;


public class DetailsFragment extends Fragment {

    TextView tvTitle;
    TextView tvContent;
    TextView tvName;
    ImageView imgAvatar;
    TextView tvDate;
    FloatingActionButton btn;
    ImageButton btnStar;



    public DetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mail_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        btn = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), BottomNavActivity.class);
                startActivity(intent);
            }
        });

        view.setVisibility(View.INVISIBLE);
    }

    public void setDetails(Exerciser exerciser) {
        tvTitle.setText(exerciser.getSubject());
        tvContent.setText(exerciser.getContent());
        tvName.setText(exerciser.getName());
        tvDate.setText(exerciser.getForamtedDate());
        imgAvatar.setImageResource(exerciser.getAvatar());

        if (!getView().isShown())
            getView().setVisibility(View.VISIBLE);
    }
}
