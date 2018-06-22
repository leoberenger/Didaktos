package fr.didaktos.didaktos.controllers.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.stetho.Stetho;

import java.util.List;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.database.DataGenerator;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.DeckViewModel;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private DeckViewModel deckViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureViewModel();

        //this.populateDatabase();
        Stetho.initializeWithDefaults(this);

        this.deckViewModel.getDeck(1)
                .observe(this, new Observer<Deck>() {
                    @Override
                    public void onChanged(@Nullable Deck deck) {
                        Log.e(TAG, "deck name= " + deck.getName());
                    }
                });

        this.deckViewModel.getDeckWithCards(1).observe(this, new Observer<DeckWithCards>() {
            @Override
            public void onChanged(@Nullable DeckWithCards deckWithCards) {
                Log.e(TAG, "deck name= " + deckWithCards.getName());
                Log.e(TAG, "card 0 key= " + deckWithCards.getCards().get(0).getKey()
                        +"card 0 value= " + deckWithCards.getCards().get(0).getValue());
            }
        });
    }

    private void populateDatabase(){

        List<Deck> decks = DataGenerator.generateDecks();
        for (int i = 0; i<decks.size(); i++){
            this.deckViewModel.createDeck(decks.get(i));
        }

        List<Card> cards = DataGenerator.generateCards();
        for (int i = 0; i<cards.size(); i++){
            this.deckViewModel.createCard(cards.get(i));
        }

    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.deckViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeckViewModel.class);
        this.deckViewModel.init();
    }
}
