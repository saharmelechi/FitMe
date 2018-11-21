package com.app.fitme.fitme;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExerciserAdapter extends RecyclerView.Adapter<ExerciserAdapter.ExerciserView> {
    List<Exerciser> exercisers;

    public ExerciserAdapter(List<Exerciser> exercisers) {
        this.exercisers = exercisers;
    }

    public class ExerciserView extends  RecyclerView.ViewHolder{
        public TextView Name;

        public TextView Age;

        public ImageView ImgProfile;

        public ImageView ImgTargil;

        public ExerciserView(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txtName);
             Age =    itemView.findViewById(R.id.txtAge);
             ImgProfile   =    itemView.findViewById(R.id.PersonPicture);
              ImgTargil =      itemView.findViewById(R.id.ImgTargil);



        }
    }


    @NonNull
    @Override
    public ExerciserView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.accomplishments, viewGroup, false);

        return new ExerciserView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciserView exerciserView, int position) {
        Exerciser res = this.exercisers.get(position);
        exerciserView.Age.setText(Float.toString(res.getAge()));
        exerciserView.ImgProfile.setImageBitmap(res.getImgProfile());
        exerciserView.ImgTargil.setImageBitmap(res.getImgExercise());
        exerciserView.Name.setText(res.getName());

    }

    @Override
    public long getItemId(int position) {
//        return (this.exercisers.get(position));
        return 0;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
