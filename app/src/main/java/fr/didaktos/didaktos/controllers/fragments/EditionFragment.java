package fr.didaktos.didaktos.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.didaktos.didaktos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditionFragment extends Fragment {


    public EditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edition, container, false);
    }

}