package fr.didaktos.didaktos.views;

import android.support.v7.widget.RecyclerView;
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
    @BindView(R.id.properties_recycler_view_item_area) TextView itemArea;
    @BindView(R.id.properties_recycler_view_item_type) TextView itemType;
    @BindView(R.id.properties_recycler_view_item_price) TextView itemPrice;
    @BindView(R.id.properties_recycler_view_item_img) ImageView itemImg;

    public DecksViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithDeck(DeckWithCards d, RequestManager glide){
        //Type
        this.itemType.setText(d.getName());

        //Area
        this.itemArea.setText(d.getName());

        //Price
        String price = "$" + d.getId();
        this.itemPrice.setText(price);

        //Img
//        glide.load(d.getPhotoUrl()).into(itemImg);
    }
}
