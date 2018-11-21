package com.app.fitme.fitme;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Exerciser {

    private String Name;

    private float Age;

    private Bitmap ImgProfile;

    private Bitmap ImgTargil;

    public Exerciser(String name, float age, Bitmap imgProfile, Bitmap imgTargil) {
        Name = name;
        Age = age;
        ImgProfile = imgProfile;
        ImgTargil = imgTargil;
    }

    public String getName() {
        return Name;
    }

    public float getAge() {
        return Age;
    }

    public Bitmap getImgProfile() {
        return ImgProfile;
    }

    public Bitmap getImgExercise() {
        return ImgTargil;
    }
}
