package fr.didaktos.didaktos.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.List;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

public class DecksStatsRecyclerAdapter extends RecyclerView.Adapter<DecksStatsViewHolder>{
    private final RequestManager glide;

    //FOR DATA
    private final List<DeckWithCards> decks;

    //CONSTRUCTOR
    public DecksStatsRecyclerAdapter(List<DeckWithCards> d, RequestManager glide){
        this.decks = d;
        this.glide = glide;
    }

    public DeckWithCards getResult(int position){
        return this.decks.get(position);
    }

    @NonNull
    @Override
    public DecksStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_stats_recycler_view_item, parent, false);

        return new DecksStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DecksStatsViewHolder viewHolder, int position){
        viewHolder.updateWithDeck(this.decks.get(position), this.glide);
    }

    @Override
    public int getItemCount() {
        return this.decks.size();
    }
}
