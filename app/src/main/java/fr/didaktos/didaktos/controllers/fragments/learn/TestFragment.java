package fr.didaktos.didaktos.controllers.fragments.learn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    private String TAG = "Test Fragment";


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test, container, false);

        DeckWithCards deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
        Log.e(TAG, "deck title = " + deck.getTitle());

        return v;
    }

}
