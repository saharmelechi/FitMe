package com.app.fitme.fitme.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ExercisersAdapter extends FirebaseRecyclerAdapter<Exerciser, ExercisersAdapter.ExerciserViewHolder> {

    public ExercisersAdapter(@NonNull FirebaseRecyclerOptions<Exerciser> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull ExerciserViewHolder holder, int position, @NonNull Exerciser exerciser) {

        //holder.imgAvatar.setImageResource(exerciser.getAvatar());
        //TODO: load with glide

        holder.tvTitle.setText(exerciser.getName());
        holder.tvSubTitle.setText(exerciser.getSubject());
        holder.tvContent.setText(exerciser.getContent());
        holder.tvDate.setText(exerciser.formatDate());
    }

    @NonNull
    @Override
    public ExerciserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exerciser_list_item, viewGroup, false);
        return new ExerciserViewHolder(convertView);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class ExerciserViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView tvTitle;
        TextView tvSubTitle;
        TextView tvContent;
        TextView tvDate;


        public ExerciserViewHolder(View view) {
            super(view);
            imgAvatar = view.findViewById(R.id.imgAvatar);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvSubTitle = view.findViewById(R.id.tvSubTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvDate = view.findViewById(R.id.tvDate);
        }
    }
}
