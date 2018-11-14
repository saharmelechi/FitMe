package com.app.fitme.fitme;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TrainerAdapter extends BaseAdapter {


    private class TrainerView{
        public String Name;

        public String Age;

        public ImageView ImgProfile;

        public ImageView ImgTargil;

        public TrainerView(String name, String age, ImageView imgProfile, ImageView imgTargil) {
            Name = name;
            Age = age;
            ImgProfile = imgProfile;
            ImgTargil = imgTargil;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

        }
        return null;
    }
}
