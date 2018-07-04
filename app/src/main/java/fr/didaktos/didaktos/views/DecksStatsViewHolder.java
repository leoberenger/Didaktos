package fr.didaktos.didaktos.views;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

public class DecksStatsViewHolder extends RecyclerView.ViewHolder {

    private String TAG = "ViewHolder";
    private int progress;

    @BindView(R.id.stats_item_title) TextView titleTextView;
    @BindView(R.id.stats_item_progressBar) ProgressBar statsProgressBar;


    public DecksStatsViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithDeck(DeckWithCards d, RequestManager glide) {

        //Title
        String title = d.getTopic() + " : " + d.getTitle();
        titleTextView.setText(title);

        //Progress bar
        float doneCards = 0;
        for (int i = 0; i < d.getCards().size(); i++) {
            if (d.getCards().get(i).getNextWorkDate() == 2) {
                doneCards++;
            }
        }

        progress = Math.round((doneCards/3)*100);
        statsProgressBar.setProgress(progress);

        if (progress < 33) {
            statsProgressBar.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (progress < 66) {
            statsProgressBar.getProgressDrawable().setColorFilter(
                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (progress < 100) {
            statsProgressBar.getProgressDrawable().setColorFilter(
                    Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            statsProgressBar.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        }

    }
}
