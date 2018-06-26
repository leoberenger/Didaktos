package fr.didaktos.didaktos.controllers.activities.learn;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.DeckViewModel;

public abstract class BaseLearnActivity extends AppCompatActivity {

    String TAG = "BaseActivity";

    protected abstract View getValueLayout();
    protected abstract void configureAnswer();
    protected DeckWithCards deck;
    protected int cardNumber;

    @BindView(R.id.activity_learn_toolbar) Toolbar mToolbar;
    @BindView(R.id.question) TextView questionTextView;
    @BindView(R.id.fragment_base_empty_frame) FrameLayout valueFrameLayout;
    @BindView(R.id.fab) FloatingActionButton nextFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_learn);
        ButterKnife.bind(this);

        //retrieve deck
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);

            //Default card number = deck size
            cardNumber = deck.getCards().size();
        }

        this.configureToolbar();
        this.configureBottomNavigation();
        valueFrameLayout.addView(getValueLayout());
        this.showNextCard();
    }

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configureBottomNavigation(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_memorize:
                    Intent intentMemorize = new Intent(getApplicationContext(), MemorizeActivity.class );
                    intentMemorize.putExtra(DeckWithCards.DECK_KEY, deck);
                    startActivity(intentMemorize);
                    return true;
                case R.id.navigation_quiz:
                    Intent intentQuiz = new Intent(getApplicationContext(), QuizActivity.class );
                    intentQuiz.putExtra(DeckWithCards.DECK_KEY, deck);
                    startActivity(intentQuiz);
                    return true;
                case R.id.navigation_test:
                    Intent intentTest = new Intent(getApplicationContext(), TestActivity.class );
                    intentTest.putExtra(DeckWithCards.DECK_KEY, deck);
                    startActivity(intentTest);
                    return true;
            }
            return false;
        }
    };

    protected void configureQuestion(){

        String question = deck.getCards().get(cardNumber).getKey();
        questionTextView.setText(question);
    }

    static void shuffleArray(String[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    protected void showNextCard(){
        int i = cardNumber-1;
        while(cardNumber >= 0 && deck.getCards().get(i).getStatus() == 2){
            i--;
        }
        cardNumber = i;

        if(cardNumber >= 0){
            configureQuestion();
            configureAnswer();
        }else{
            endOfDeck();
        }
    }

    protected void endOfDeck(){
        Toast.makeText(this, "End of the deck", Toast.LENGTH_LONG).show();
    }


}
