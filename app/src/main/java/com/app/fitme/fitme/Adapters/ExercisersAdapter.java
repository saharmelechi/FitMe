package com.app.fitme.fitme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.app.fitme.fitme.Activities.MainActivity;
import com.app.fitme.fitme.Fragments.ExerciserListFragment;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ExercisersAdapter extends FirebaseRecyclerAdapter<Exerciser, ExercisersAdapter.ExerciserViewHolder> {

    FragmentActivity main;
    public ExercisersAdapter(@NonNull FirebaseRecyclerOptions<Exerciser> options, FragmentActivity activity) {
        super(options);
        main = activity;
    }


    @Override
    protected void onBindViewHolder(@NonNull final ExerciserViewHolder holder, final int position, @NonNull final Exerciser exerciser) {

        Glide.with(holder.imgAvatar).load(exerciser.getAvatar()).into(holder.imgAvatar);
        holder.tvTitle.setText(exerciser.getName());
        holder.tvSubTitle.setText(exerciser.getSubject());
        holder.tvContent.setText(exerciser.getContent());
        holder.tvDate.setText(exerciser.formatDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ExerciserListFragment.SelectionListener) main).onItemSeleceted(exerciser);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.context_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().contains("Edit")){
                            ((ExerciserListFragment.SelectionListener) main).onItemSeleceted(exerciser);
                        } else  {
                            getRef(position).removeValue();
                            //TODO:Delete the task
                        }

                        return true;
                    }
                });

                popup.show();
                return true;
            }
        });

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
