package com.app.fitme.fitme.Adapters;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.app.fitme.fitme.Fragments.ExerciserListFragment;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.R;


import java.util.List;

public class ExercisersAdapter extends BaseAdapter {

    List<Exerciser> exercisers;
    ExerciserListFragment.SelectionListener listener;

    public ExercisersAdapter(List<Exerciser> exercisers, ExerciserListFragment.SelectionListener listener){
        this.exercisers = exercisers;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return exercisers.size();
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
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_list_item, parent, false);

            convertView.setTag(new ViewHolder(convertView));
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        Exerciser exerciser = exercisers.get(position);

        holder.imgAvatar.setImageResource(exerciser.getAvatar());
        holder.tvTitle.setText(exerciser.getName());
        holder.tvSubTitle.setText(exerciser.getSubject());
        holder.tvContent.setText(exerciser.getContent());

        holder.tvDate.setText(exerciser.getForamtedDate());





        return convertView;
    }

    class ViewHolder{

        ImageView imgAvatar;
        TextView tvTitle;
        TextView tvSubTitle;
        TextView tvContent;
        TextView tvDate;


        public ViewHolder(View view) {
            imgAvatar = view.findViewById(R.id.imgAvatar);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvSubTitle = view.findViewById(R.id.tvSubTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvDate = view.findViewById(R.id.tvDate);
        }
    }
}
