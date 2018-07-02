package fr.didaktos.didaktos.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.fragments.EditionFragment;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.DeckViewModel;

public class EditionActivity extends AppCompatActivity
    implements EditionFragment.OnEditionListener{

    String TAG = "EditionActivity";
    public static String EDITION_KEY = "EDITION";
    private boolean isEditionMode = false;

    //FOR DATA
    private DeckViewModel viewModel;
    private DeckWithCards deck;

    @BindView(R.id.activity_edition_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        this.configureViewModel();
        this.configureToolbar();

        //if Edit, retrieve deck
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);
            isEditionMode = true;
        }

        this.viewModel.getAllDecks().observe(this,this::configureAndShowEditionFragment);
    }

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void  configureAndShowEditionFragment(List<Deck> decks){

        EditionFragment fragment = (EditionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_edition_layout);

        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(EDITION_KEY, isEditionMode);

            //if Edition mode, send Deck to edit
            if(isEditionMode) {
                bundle.putParcelable(DeckWithCards.DECK_KEY, deck);
            }else {
                bundle.putLong(DeckWithCards.NEXT_DECK_ID_KEY, decks.size());
            }

            fragment = new EditionFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_edition_fragment, fragment)
                    .commit();
        }
    }


    // -----------------
    // CALLBACK
    // -----------------

    @Override
    public void onDeckEdited(DeckWithCards deck) {
        if(isEditionMode){
            updateDeck(deck);
            Toast.makeText(this, "Deck edited", Toast.LENGTH_LONG).show();
        }else{
            createDeck(deck);
            Toast.makeText(this, "Deck added", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // -----------------
    // RETRIEVE DATA
    // -----------------

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeckViewModel.class);
        this.viewModel.init();
    }

    private void updateDeck(DeckWithCards deck){
        //Update Deck
        Deck deckToUpdate = new Deck(deck.getId(), deck.getTopic(), deck.getTitle(), deck.getDescription(), deck.getImgUrl());
        this.viewModel.updateDeck(deckToUpdate);

        //Update Cards
        for(int i = 0; i<deck.getCards().size(); i++){
            this.viewModel.updateCard(deck.getCards().get(i));
        }
    }

    private void createDeck(DeckWithCards deck) {
        //Create deck
        Deck deckToCreate = new Deck(deck.getId(), deck.getTopic(), deck.getTitle(), deck.getDescription(), deck.getImgUrl());
        this.viewModel.createDeck(deckToCreate);

        //Create cards
        for(int i = 0; i<deck.getCards().size(); i++){
            this.viewModel.createCard(deck.getCards().get(i));
        }

    }
}
