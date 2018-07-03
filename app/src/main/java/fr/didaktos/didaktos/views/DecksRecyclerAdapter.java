package fr.didaktos.didaktos.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;

import java.util.List;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

import static android.content.Context.MODE_PRIVATE;

public class DecksRecyclerAdapter  extends RecyclerView.Adapter<DecksViewHolder>{
    private final RequestManager glide;

    //FOR DATA
    private final List<DeckWithCards> decks;
    private SharedPreferences mPreferences;

    //CONSTRUCTOR
    public DecksRecyclerAdapter(List<DeckWithCards> d, RequestManager glide){
        this.decks = d;
        this.glide = glide;
    }

    public DeckWithCards getResult(int position){
        return this.decks.get(position);
    }

    @NonNull
    @Override
    public DecksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_recycler_view_item, parent, false);

        this.mPreferences = context.getSharedPreferences(DeckWithCards.DECKS_KEY, MODE_PRIVATE);

        return new DecksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DecksViewHolder viewHolder, int position){
        int selectedPosition = mPreferences.getInt("selectedPosition", 0);
        viewHolder.updateWithDeck(this.decks.get(position), this.glide, position, selectedPosition);
    }

    @Override
    public int getItemCount() {
        return this.decks.size();
    }
}
