package fr.didaktos.didaktos.controllers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.activities.LearnActivity;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private final String TAG = "DetailFragment";

    //FOR DATA
    private DeckWithCards deck;
    private boolean hasCardToWork;

    //FOR DESIGN
    @BindView(R.id.detail_description) TextView textViewDescription;
    @BindView(R.id.detail_nb_cards) TextView textViewNbCards;
    @BindView(R.id.detail_topic) TextView textViewTopic;
    @BindView(R.id.detail_title) TextView textViewTitle;
    @BindView(R.id.detail_examples_0) TextView textViewExamples0;
    @BindView(R.id.detail_examples_1) TextView textViewExamples1;
    @BindView(R.id.detail_examples_2) TextView textViewExamples2;
    @BindView(R.id.detail_examples_3) TextView textViewExamples3;
    @BindView(R.id.detail_img) ImageView imageViewDescription;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);


        if(getArguments()!=null){
            deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
            hasCardToWork = getArguments().getBoolean(DeckWithCards.CARDTOWORK_KEY);
            updateShownDeck(deck);
        }

        return view;
    }

    private void updateShownDeck(DeckWithCards d){

        //Img
        if(!d.getImgUrl().isEmpty()) {
            Glide.with(this).load(d.getImgUrl()).into(imageViewDescription);
        }

        //Description
        textViewTopic.setText(d.getTopic());
        textViewTitle.setText(d.getTitle());
        textViewDescription.setText(d.getDescription());
        String nbCards = d.getCards().size() + " cartes";
        textViewNbCards.setText(nbCards);

        //Examples
        TextView [] examples = new TextView[]{textViewExamples0, textViewExamples1, textViewExamples2, textViewExamples3};
        for (int i = 0; i<examples.length; i++){
            String str = "- " + d.getCards().get(i).getKey() + " (" + d.getCards().get(i).getValue() + ")";
            examples[i].setText(str);
        }
    }

    @OnClick(R.id.detail_btn)
    public void onClickLearnButton(){
        if(hasCardToWork){
            Intent intent = new Intent(getContext(), LearnActivity.class);
            intent.putExtra(DeckWithCards.DECK_KEY, deck);
            startActivity(intent);
        }else {
            Toast.makeText(getContext(), "Aucune carte à apprendre aujourd'hui!", Toast.LENGTH_LONG).show();
        }
    }

}
