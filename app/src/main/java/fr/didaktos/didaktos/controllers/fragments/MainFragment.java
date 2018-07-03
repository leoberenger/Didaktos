package fr.didaktos.didaktos.controllers.fragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.utils.ItemClickSupport;
import fr.didaktos.didaktos.views.DecksRecyclerAdapter;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    String TAG = "Main Fragment";

    //FOR CALLBACK
    private OnDecksListSelectedListener mCallback;

    public interface OnDecksListSelectedListener{
        void onDeckSelected(long deckId);
    }

    //FOR DATA
    private long deckId = -1;
    private List<DeckWithCards> decks;
    private ArrayList<DeckWithCards> mDeckArrayList;
    private SharedPreferences mPreferences;


    // FOR DESIGN
    @BindView(R.id.decks_recycler_view) RecyclerView recyclerView;
    private DecksRecyclerAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);

        this.mPreferences = getContext().getSharedPreferences(DeckWithCards.DECKS_KEY, MODE_PRIVATE);
        mPreferences.edit().putInt("selectedPosition", 0).apply();

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();

        if(getArguments() != null) {
            mDeckArrayList = getArguments().getParcelableArrayList(DeckWithCards.DECKS_KEY);
            if(mDeckArrayList.size() != 0) {
                this.updateDecksList(mDeckArrayList);
            }
        }

        return view;
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureRecyclerView(){
        this.decks = new ArrayList<>();
        this.adapter = new DecksRecyclerAdapter(this.decks, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_main_recycler_view_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    mPreferences.edit().putInt("selectedPosition", position).apply();
                    adapter.notifyDataSetChanged();

                    deckId = adapter.getResult(position).getId();
                    mCallback.onDeckSelected(deckId);
                });
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateDecksList(List<DeckWithCards> decks){
        this.decks.clear();
        this.decks.addAll(decks);
        adapter.notifyDataSetChanged();
    }


    // -------------------------
    // COMMUNICATE WITH ACTIVITY
    // -------------------------

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (OnDecksListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDecksListSelectedListener");
        }
    }
}
