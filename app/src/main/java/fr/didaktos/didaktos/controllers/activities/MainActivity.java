package fr.didaktos.didaktos.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.views.DeckViewModel;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private DeckViewModel deckViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.populateDatabase();
    }

    private void populateDatabase(){

        Deck [] decks = new Deck[2];
/*        for (int i = 0; i<decks.length; i++){
            decks[i] = new Deck();
        }
  */
        String [] deck0Keys = {"eat", "drink", "sleep", "study", "work",
                                "travel", "think", "ask", "leave", "speak"};
        String [] deck0Values = {"manger", "boire", "dormir", "étudier", "travailler",
                                 "voyager", "penser", "demander", "partir", "parler"};
        String [] deck1Keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
        String [] deck1Values = {"Hydrogène", "Hélium", "Lithium", "Béryllium", "Bore",
                                 "Carbone", "Azote", "Oxygène", "Fluor", "Néon"};

        decks[0] = new Deck("English", "https://picsum.photos/200/200/?image=820",
                deck0Keys.length, deck0Keys, deck0Values);

        decks[1] = new Deck("Physique : Atomes", "https://picsum.photos/200/200/?image=20",
                deck1Keys.length, deck1Keys, deck1Values);

        for(int i = 0; i<decks.length;i++) {
            this.deckViewModel.createDeck(decks[i]);
        }
    }
}
