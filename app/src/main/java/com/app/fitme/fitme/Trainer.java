package com.app.fitme.fitme;

import android.widget.ImageView;

public class Trainer {

    private String Name;

    private String Age;

    private ImageView ImgProfile;

    private ImageView ImgTargil;

    public Trainer(String name, String age, ImageView imgProfile, ImageView imgTargil) {
        Name = name;
        Age = age;
        ImgProfile = imgProfile;
        ImgTargil = imgTargil;
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

    public ImageView getImgProfile() {
        return ImgProfile;
    }

    public ImageView getImgTargil() {
        return ImgTargil;
    }
}
