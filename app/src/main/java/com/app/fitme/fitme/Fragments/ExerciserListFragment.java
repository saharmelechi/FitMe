package com.app.fitme.fitme.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;


import com.app.fitme.fitme.Adapters.ExercisersAdapter;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.Models.FireBaseModel;
import com.app.fitme.fitme.R;

import java.util.List;

public class ExerciserListFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    List<Exerciser> exercisers;
    RecyclerView lstExerciser;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((SelectionListener) getActivity()).onItemSeleceted(exercisers.get(position));
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        ((SelectionListener) getActivity()).onItemSeleceted(exercisers.get(position));
//    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.context_menu, popup.getMenu());
        //TODO: link delete to exercisers.get(position).delete()
        //TODO: link Edit to exercisers.get(position).Edit()
        popup.show();
        return true;
    }


    public interface SelectionListener {
        void onItemSeleceted(Exerciser exerciser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView rootView = (RecyclerView) inflater.inflate(R.layout.exerciser_list_fragment, container, false);
        lstExerciser = (RecyclerView) rootView.findViewById(R.id.list);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ExercisersAdapter adapter = new ExercisersAdapter(FireBaseModel.instance.getAllMessages());


        lstExerciser.setLayoutManager(new LinearLayoutManager(this.getContext()));
        lstExerciser.setAdapter(adapter);


        //getView().setOnItemLongClickListener(this);
        //getView().setOnItemClickListener(this);


    }

}
