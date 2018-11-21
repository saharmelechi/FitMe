package com.app.fitme.fitme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExerciserAdapter extends BaseAdapter {
    List<Exerciser> exercisers;

    public ExerciserAdapter(List<Exerciser> exercisers) {
        this.exercisers = exercisers;
    }

    private class ExerciserView {
        public TextView Name;

        public TextView Age;

        public ImageView ImgProfile;

        public ImageView ImgTargil;

        public ExerciserView(View name, View age, View imgProfile, View imgTargil) {
            Name = (TextView) name;
            Age = (TextView) age;
            ImgProfile = (ImageView) imgProfile;
            ImgTargil = (ImageView) imgTargil;
        }
    }

    @Override
    public int getCount() {
        return this.exercisers.size();
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

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            convertView = layoutInflater.inflate(R.layout.accomplishments, parent, false);

            ExerciserView trainer = new ExerciserView(convertView.findViewById(R.id.txtName),
                    convertView.findViewById(R.id.txtAge),
                    convertView.findViewById(R.id.PersonPicture),
                    convertView.findViewById(R.id.ImgTargil));
            convertView.setTag(trainer);

        }

        ExerciserView tv = (ExerciserView) convertView.getTag();
        Exerciser t = exercisers.get(position);
        tv.Age.setText(Float.toString(t.getAge()));
        tv.ImgProfile.setImageBitmap(t.getImgProfile());
        tv.ImgTargil.setImageBitmap(t.getImgExercise());
        tv.Name.setText(t.getName());

        return convertView;
    }
}
