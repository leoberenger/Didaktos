package fr.didaktos.didaktos.views;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

public class DecksViewHolder extends RecyclerView.ViewHolder {

    private String TAG = "ViewHolder";

    @BindView(R.id.decks_recycler_view_item_layout) LinearLayout itemLayout;
    @BindView(R.id.decks_recycler_view_item_img) ImageView itemImg;
    @BindView(R.id.decks_recycler_view_item_topic) TextView itemTopic;
    @BindView(R.id.decks_recycler_view_item_title) TextView itemTitle;

    public DecksViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithDeck(DeckWithCards d, RequestManager glide, int currentPosition, int selectedPosition){

        if(selectedPosition == currentPosition){
            this.itemLayout.setBackgroundColor(Color.parseColor("#008ba3"));
            this.itemTopic.setTextColor(Color.WHITE);
            this.itemTitle.setTextColor(Color.WHITE);
        }else {
            this.itemLayout.setBackgroundResource(R.drawable.border);
            this.itemTopic.setTextColor(Color.parseColor("#008ba3"));
            this.itemTitle.setTextColor(Color.parseColor("#00bcd4"));
        }
        //Img
        glide.load(d.getImgUrl()).into(itemImg);

        //Topic
        this.itemTopic.setText(d.getTopic());

        //Title
        this.itemTitle.setText(d.getTitle());
    }
}
