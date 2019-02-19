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

public class ExerciserListFragment extends Fragment implements AdapterView.OnItemClickListener {

    List<Exerciser> exercisers;
    RecyclerView lstExerciser;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((SelectionListener) getActivity()).onItemSeleceted(exercisers.get(position));
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
    public void onStart() {
            super.onStart();
            ((ExercisersAdapter)lstExerciser.getAdapter()).startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((ExercisersAdapter)lstExerciser.getAdapter()).stopListening();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ExercisersAdapter adapter = new ExercisersAdapter(FireBaseModel.instance.getAllMessages(), getActivity());

        lstExerciser.setLayoutManager(new LinearLayoutManager(this.getContext()));
        lstExerciser.setAdapter(adapter);
    }

}
