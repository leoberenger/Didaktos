package fr.didaktos.didaktos.controllers.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private String title;
    private String topic;
    private String description;
    private int currentRowsNb = 0;
    private String [] keys;
    private String [] values;
    private long nextDeckId;
    private long nextCardId;

    @BindView(R.id.edition_table) LinearLayout tableLayout;
    @BindView(R.id.edition_button) Button editionButton;
    @BindView(R.id.edition_topic) EditText topicEditText;
    @BindView(R.id.edition_title) EditText titleEditText;
    @BindView(R.id.edition_description) EditText descriptionEditText;

    public EditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edition, container, false);
        ButterKnife.bind(this, view);

        //Retrieve data
        isEditionMode = getArguments().getBoolean(EditionActivity.EDITION_KEY);

        if(isEditionMode) {
            deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
            this.createRows(deck.getCards().size());
            this.showDeckCurrentValues(deck);
        }else {
            nextDeckId = getArguments().getLong(DeckWithCards.NEXT_DECK_ID_KEY);
            nextCardId = getArguments().getLong(DeckWithCards.NEXT_CARD_ID_KEY);
            this.createRows(10);
        }

        this.configureEditionButton();
        return view;
    }


    // -------------------------
    // CONFIGURE VIEW
    // -------------------------

    private void configureEditionButton(){
        editionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDataFromUserInput();

                boolean hasEmptyKeyOrValue = false;
                for (int i = 0; i < currentRowsNb; i++) {
                    if (keys[i].isEmpty() || values[i].isEmpty()) {
                        hasEmptyKeyOrValue = true;
                    }
                }

                if (topic.equals("")) {
                    Toast.makeText(getContext(), "Il manque la matière", Toast.LENGTH_LONG).show();
                } else if (title.equals("")) {
                    Toast.makeText(getContext(), "Il manque un titre", Toast.LENGTH_LONG).show();
                } else if (hasEmptyKeyOrValue){
                    Toast.makeText(getContext(), "Des cases sont vides", Toast.LENGTH_LONG).show();
                }else{

                    deckEdited = (isEditionMode)? editDeck() : createDeck();

                    mCallback.onDeckEdited(deckEdited);
                }
            }
        });
    }

    private void createRows(int totalRows){

        for(int i = 0; i< totalRows; i++){
            this.createNewRow();
        }
    }

    private void createNewRow(){
        View row = getLayoutInflater().inflate(R.layout.edition_row, null);

        TextView id = (TextView)row.findViewById(R.id.edition_id);
        String rowId = String.valueOf(currentRowsNb+1);
        id.setText(rowId);

        EditText key = (EditText)row.findViewById(R.id.edition_key);
        key.setTag("key"+currentRowsNb);

        EditText valueEditText = (EditText)row.findViewById(R.id.edition_value);
        valueEditText.setTag("value"+currentRowsNb);

        currentRowsNb++;

        tableLayout.addView(row);
    }


    // -------------------------
    // RETRIEVE DATA
    // -------------------------

    private void showDeckCurrentValues(DeckWithCards d){

        titleEditText.setText(d.getTitle());
        topicEditText.setText(d.getTopic());
        descriptionEditText.setText(d.getDescription());

        for(int i = 0; i<d.getCards().size(); i++) {
            //Get and show Keys
            String cardKey = d.getCards().get(i).getKey();
            EditText key =  (EditText)tableLayout.findViewWithTag("key"+i);
            key.setText(cardKey);

            //Get and show Values
            String cardValue = d.getCards().get(i).getValue();
            EditText value =  (EditText)tableLayout.findViewWithTag("value"+i);
            value.setText(cardValue);
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

        keys = new String [currentRowsNb];
        values = new String[currentRowsNb];

        for(int i = 0; i<currentRowsNb ;i++){
            EditText key =  (EditText)tableLayout.findViewWithTag("key"+i);
            EditText value =  (EditText)tableLayout.findViewWithTag("value"+i);
            keys[i] = key.getText().toString();
            values[i] = value.getText().toString();
        }

    }

    private DeckWithCards editDeck(){
        Deck editedDeck = new Deck(deck.getId(), topic, title, description, deck.getImgUrl());

        ArrayList<Card> cardsEdited = new ArrayList<>();

        for(int i = 0; i<deck.getCards().size(); i++) {
            Card editedCard = new Card(deck.getCards().get(i).getId(), deck.getId(), keys[i], values[i], 0);
            cardsEdited.add(editedCard);
        }

        return new DeckWithCards(editedDeck, cardsEdited);

    }

    private DeckWithCards createDeck(){
        Deck newDeck = new Deck(nextDeckId, topic, title, description, "");

        ArrayList<Card> newCards = new ArrayList<>();

        for(int i = 0; i<keys.length; i++){
            Card newCard = new Card((nextCardId), nextDeckId, keys[i], values[i], 0);
            newCards.add(newCard);
            nextCardId++;
        }

        return new DeckWithCards(newDeck, newCards);
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
