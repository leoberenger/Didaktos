package fr.didaktos.didaktos.controllers.activities.learn;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import fr.didaktos.didaktos.controllers.fragments.MemorizeFragment;
import fr.didaktos.didaktos.models.DeckWithCards;

public class LearnActivity extends AppCompatActivity
    implements
        View.OnClickListener,
        MemorizeFragment.OnMemorizeAnswerListener {

    String TAG = "LearnActivity";

    private DeckWithCards deck;
    private int cardNumber;

    @BindView(R.id.activity_learn_toolbar) Toolbar mToolbar;
    @BindView(R.id.question) TextView questionTextView;
    @BindView(R.id.fragment_base_empty_frame) FrameLayout valueFrameLayout;
    @BindView(R.id.next_fab) FloatingActionButton nextFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_learn);
        ButterKnife.bind(this);

        //retrieve deck
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);

            //Default card number = deck size
            cardNumber = deck.getCards().size()-1;
        }

        Bundle args = new Bundle();
        args.putString(DeckWithCards.ANSWER_KEY, deck.getCards().get(cardNumber).getValue());
        replaceCurrentFragment(new MemorizeFragment(), args);

        this.configureToolbar();
        this.configureBottomNavigation();
        this.configureNextFab();
        this.configureQuestion();
    }

    //---------------------------
    //CONFIGURATION OF VIEW
    //--------------------------

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
            Fragment fragment = null;
            Bundle args = new Bundle();

            switch (item.getItemId()) {
                case R.id.navigation_memorize:
                    fragment = new MemorizeFragment();
                    args.putString(DeckWithCards.ANSWER_KEY, deck.getCards().get(cardNumber).getValue());
                    break;
                case R.id.navigation_quiz:
                    fragment = new MemorizeFragment();
                    break;
                case R.id.navigation_test:
                    fragment = new MemorizeFragment();
                    break;
            }
            replaceCurrentFragment(fragment, args);
            return true;
        }
    };

    private void replaceCurrentFragment(Fragment fragment, Bundle args){
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_base_empty_frame, fragment);
        transaction.commit();
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

    private void configureQuestion(){
        String question = deck.getCards().get(cardNumber).getKey();
        questionTextView.setText(question);
    }

    private void configureAnswer(){
        Bundle args = new Bundle();
        args.putString(DeckWithCards.ANSWER_KEY, deck.getCards().get(cardNumber).getValue());
        replaceCurrentFragment(new MemorizeFragment(), args);
    }

    private void configureNextFab(){
        nextFab.setOnClickListener(this);
        nextFab.setVisibility(View.INVISIBLE);
    }


    protected void showNextCard(){

    }

    protected void endOfDeck(){
        Toast.makeText(this, "End of the deck", Toast.LENGTH_LONG).show();
    }

    //-----------------------------------
    // CALLBACKS
    //-----------------------------------

    @Override
    public void onAnswer() {
        nextFab.setVisibility(View.VISIBLE);
        cardNumber--;
        Log.e(TAG, "memorize answered");
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "fab clicked");
        int i = cardNumber;
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
}
