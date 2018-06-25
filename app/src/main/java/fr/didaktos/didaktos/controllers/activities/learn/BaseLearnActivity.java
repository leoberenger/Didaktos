package fr.didaktos.didaktos.controllers.activities.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

public abstract class BaseLearnActivity extends AppCompatActivity {

    protected abstract View getValueLayout();
    protected abstract int getCardNumber();
    protected abstract void configureAnswer();
    protected DeckWithCards deck;

    @BindView(R.id.activity_learn_toolbar) Toolbar mToolbar;
    @BindView(R.id.question) TextView questionTextView;
    @BindView(R.id.fragment_base_empty_frame) FrameLayout valueFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_learn);
        ButterKnife.bind(this);

        //retrieve deck
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);
        }

        this.configureToolbar();
        this.configureBottomNavigation();
        valueFrameLayout.addView(getValueLayout());
        this.configureQuestion(deck.getCards().get(getCardNumber()).getKey());
        this.configureAnswer();

    }

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configureBottomNavigation(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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

    private void configureQuestion(String q){
        questionTextView.setText(q);
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
}
