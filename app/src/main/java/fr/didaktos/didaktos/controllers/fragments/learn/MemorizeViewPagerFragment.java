package fr.didaktos.didaktos.controllers.fragments.learn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemorizeViewPagerFragment extends Fragment {

    private String TAG = "MemorizePagerFragment";

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    public MemorizeViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.memorize_viewpager, container, false);

        DeckWithCards deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
        Log.e(TAG, "deck title = " + deck.getTitle());

        this.configureViewPager(v, deck);

        return v;
    }

    private void configureViewPager(View v, DeckWithCards d){
        viewPager = (ViewPager) v.findViewById(R.id.memorize_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), d);
        viewPager.setAdapter(viewPagerAdapter);
    }

}
