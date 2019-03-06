package com.app.fitme.fitme.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.app.fitme.fitme.Adapters.ExercisersAdapter;
import com.app.fitme.fitme.Models.Exerciser;
import com.app.fitme.fitme.Models.FireBaseModel;
import com.app.fitme.fitme.R;

import java.util.List;

public class ExerciserListFragment extends Fragment implements AdapterView.OnItemClickListener {

    List<Exerciser> exercisers;
    RecyclerView lstExerciser;
    private String ClubName;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((SelectionListener) getActivity()).onItemSeleceted(exercisers.get(position),false);
    }


    public interface SelectionListener {
        void onItemSeleceted(Exerciser exerciser, boolean EditMode);
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

        // If club name has changed
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());

        String tempClub = preferences.getString("ClubName","default_value");
        if (!ClubName.equals(tempClub)) {
            ClubName = tempClub;
            createExerciseAdapter();
        }

        if (lstExerciser.getAdapter() != null) {
            ((ExercisersAdapter) lstExerciser.getAdapter()).startListening();
        }
    }

    public void createExerciseAdapter() {
        if (lstExerciser.getAdapter() != null){
            ((ExercisersAdapter) lstExerciser.getAdapter()).stopListening();
        }
        // Refresh data hooks
        ExercisersAdapter adapter = new ExercisersAdapter(FireBaseModel.instance.getAllExercises(ClubName), getActivity());
        lstExerciser.setLayoutManager(new LinearLayoutManager(this.getContext()));
        lstExerciser.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lstExerciser.getAdapter() != null) {
            ((ExercisersAdapter) lstExerciser.getAdapter()).stopListening();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        ClubName = preferences.getString("ClubName","default_value");
        createExerciseAdapter();

    }

}
