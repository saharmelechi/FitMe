package com.app.fitme.fitme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrainerAdapter extends BaseAdapter {
    List<Trainer> trainers;

    public TrainerAdapter(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    private class TrainerView{
        public TextView Name;

        public TextView Age;

        public ImageView ImgProfile;

        public ImageView ImgTargil;

        public TrainerView(View name, View age, View imgProfile, View imgTargil) {
            Name = (TextView) name;
            Age = (TextView) age;
            ImgProfile = (ImageView) imgProfile;
            ImgTargil = (ImageView) imgTargil;
        }
    }

    @Override
    public int getCount() {
        return this.trainers.size();
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
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            convertView = layoutInflater.inflate(R.layout.accomplishments,parent, false);

            TrainerView trainer = new TrainerView( convertView.findViewById(R.id.txtName),
                                                    convertView.findViewById(R.id.txtAge),
                                                    convertView.findViewById(R.id.PersonPicture),
                                                    convertView.findViewById(R.id.ImgTargil));
                convertView.setTag(trainer);

        }
        //Need to Implement here get tag
        

        return convertView;
    }
}
