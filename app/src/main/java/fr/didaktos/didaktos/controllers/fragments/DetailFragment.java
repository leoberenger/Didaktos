package fr.didaktos.didaktos.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    String TAG = "DetailFragment";

    //FOR DATA
    private DeckWithCards deck;

    //FOR DESIGN
    @BindView(R.id.detail_description) TextView textViewDescription;
    @BindView(R.id.detail_examples) TextView textViewExamples;
    @BindView(R.id.detail_img) ImageView imageViewDescription;
    @BindView(R.id.detail_btn) Button button;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null) {
            deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
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
        textViewDescription.setText(d.getName());

        //Examples
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<d.getCards().size(); i++){
            String str = d.getCards().get(i).getKey() + "/" + d.getCards().get(i).getValue()+ " ";
            stringBuilder.append(str);
        }
        String examples = stringBuilder.toString();
        textViewExamples.setText(examples);
    }

}
