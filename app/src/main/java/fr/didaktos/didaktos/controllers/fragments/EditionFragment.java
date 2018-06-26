package fr.didaktos.didaktos.controllers.fragments;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.activities.EditionActivity;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditionFragment extends Fragment {

    String TAG = "EditionFragment";

    //FOR CALLBACK
    OnEditionListener mCallback;

    public interface OnEditionListener{
        void onDeckEdited(DeckWithCards deck);
    }

    private boolean isEditionMode;
    private DeckWithCards deck;
    private DeckWithCards deckEdited;
    private EditText [] keyEditTexts;
    private EditText [] valueEditTexts;
    private String title;
    private String topic;
    private String description;
    private String [] keys;
    private String [] values;
    private long nextDeckId;



    @BindView(R.id.edition_button) Button editionButton;
    @BindView(R.id.edition_topic) EditText topicEditText;
    @BindView(R.id.edition_title) EditText titleEditText;
    @BindView(R.id.edition_description) EditText descriptionEditText;
    @BindView(R.id.edition_key_0_edit_text) EditText keyEditText0;
    @BindView(R.id.edition_key_1_edit_text) EditText keyEditText1;
    @BindView(R.id.edition_key_2_edit_text) EditText keyEditText2;
    @BindView(R.id.edition_key_3_edit_text) EditText keyEditText3;
    @BindView(R.id.edition_value_0_edit_text) EditText valueEditText0;
    @BindView(R.id.edition_value_1_edit_text) EditText valueEditText1;
    @BindView(R.id.edition_value_2_edit_text) EditText valueEditText2;
    @BindView(R.id.edition_value_3_edit_text) EditText valueEditText3;

    public EditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edition, container, false);
        ButterKnife.bind(this, view);

        keyEditTexts = new EditText[]{keyEditText0, keyEditText1, keyEditText2, keyEditText3};
        valueEditTexts = new EditText[]{valueEditText0, valueEditText1, valueEditText2, valueEditText3};
        keys = new String[keyEditTexts.length];
        values = new String[valueEditTexts.length];

        isEditionMode = getArguments().getBoolean(EditionActivity.EDITION_KEY);

        if(isEditionMode) {
            deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
            this.showDeckCurrentValues(deck);
        }else {
            nextDeckId = getArguments().getLong(DeckWithCards.NEXT_DECK_ID_KEY);
        }

        this.configureEditionButton();

        return view;
    }

    private void configureEditionButton(){
        editionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDataFromUserInput();

                if(topic.equals("")){
                    Toast.makeText(getContext(), "No Topic entered", Toast.LENGTH_LONG).show();
                }else if(title.equals("")){
                    Toast.makeText(getContext(), "No Title entered", Toast.LENGTH_LONG).show();
                }else{
                    //FOR EDITION
                    if(isEditionMode){
                        deckEdited = new DeckWithCards();
                        deckEdited.setId(deck.getId());
                        Log.e(TAG, "deck updated id = " + deckEdited.getId());
                        deckEdited.setTopic(topic);
                        deckEdited.setTitle(title);
                        deckEdited.setDescription(description);
                        deckEdited.setImgUrl(deck.getImgUrl());

                        ArrayList<Card> cardsEdited = new ArrayList<>(deck.getCards());
                        for(int i = 0; i<4; i++) {
                            cardsEdited.get(i).setDeckId(deck.getId());
                            cardsEdited.get(i).setKey(keys[i]);
                            cardsEdited.get(i).setValue(values[i]);
                            cardsEdited.get(i).setStatus(0);
                        }
                        deckEdited.setCards(cardsEdited);

                    }else{
                    //FOR CREATION
                        Deck newDeck = new Deck(nextDeckId, topic, title, description, "");

                        ArrayList<Card> newCards = new ArrayList<>();
                        int i = 0;
                        while((i<keys.length) && (!keys[i].equals(""))){
                            Card newCard = new Card(nextDeckId, keys[i], values[i], 0);
                            newCards.add(newCard);
                            i++;
                        }
                        deckEdited = new DeckWithCards(newDeck, newCards);
                    }

                    mCallback.onDeckEdited(deckEdited);
                }
            }
        });
    }

    private void showDeckCurrentValues(DeckWithCards d){

        titleEditText.setText(d.getTitle());
        topicEditText.setText(d.getTopic());
        descriptionEditText.setText(d.getDescription());

        for(int i = 0; i<d.getCards().size(); i++) {
            //Get and show Keys
            String cardKey = d.getCards().get(i).getKey();
            keyEditTexts[i].setText(cardKey);

            //Get and show Values
            String cardValue = d.getCards().get(i).getValue();
            valueEditTexts[i].setText(cardValue);
        }
        /*
        Glide.with(this)
                .load(p.getPhotoUrl())
                .into(this.imageViewPreview);
        uriImageSelected = Uri.parse(p.getPhotoUrl());
        */
        }

    private void getDataFromUserInput(){

        topic = topicEditText.getText().toString();
        title = titleEditText.getText().toString();
        description = descriptionEditText.getText().toString();

        for(int i = 0; i<keys.length ;i++){
            keys[i] = keyEditTexts[i].getText().toString();
            values[i] = valueEditTexts[i].getText().toString();
        }
    }

    // -------------------------
    // COMMUNICATE WITH ACTIVITY
    // -------------------------

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (EditionFragment.OnEditionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPropertiesListSelectedListener");
        }
    }

}
