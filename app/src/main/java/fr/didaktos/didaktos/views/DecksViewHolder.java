package fr.didaktos.didaktos.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;

public class DecksViewHolder extends RecyclerView.ViewHolder {

    private String TAG = "ViewHolder";

    @BindView(R.id.decks_recycler_view_item_img) ImageView itemImg;
    @BindView(R.id.decks_recycler_view_item_topic) TextView itemTopic;
    @BindView(R.id.decks_recycler_view_item_title) TextView itemTitle;
    @BindView(R.id.decks_recycler_view_item_nbCards) TextView itemNbCards;

    public DecksViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithDeck(DeckWithCards d, RequestManager glide){

        //Img
        glide.load(d.getImgUrl()).into(itemImg);

        //Topic
        this.itemTopic.setText(d.getTopic());

        //Title
        this.itemTitle.setText(d.getTitle());

        //Number of Cards
        String nbCards = d.getCards().size() + " cards";
        this.itemNbCards.setText(nbCards);
    }
}
