package fr.didaktos.didaktos.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.fragments.learn.MemorizeFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.QuizFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.TestFragment;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.DeckViewModel;

public class LearnActivity extends AppCompatActivity
    implements
        MemorizeFragment.OnMemorizeAnswerListener,
        QuizFragment.OnQuizAnswerListener,
        TestFragment.OnTestAnswerListener{

    String TAG = "LearnActivity";

    //DATABASE
    private DeckViewModel deckViewModel;

    //DATA
    private DeckWithCards deck;
    private int currentCardNb;
    private int nextFragmentNb = 0;

    //DESIGN
    @BindView(R.id.activity_learn_toolbar) Toolbar mToolbar;
    @BindView(R.id.question) TextView questionTextView;
    @BindView(R.id.next_fab) FloatingActionButton nextFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_learn);
        ButterKnife.bind(this);

        deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);
        currentCardNb = deck.getCards().size()-1;

        this.configureViewModel();

        this.configureToolbar();
        this.configureBottomNavigation();
        this.configureNextCard();
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

            switch (item.getItemId()) {
                case R.id.navigation_memorize: nextFragmentNb = 0; break;
                case R.id.navigation_quiz: nextFragmentNb = 1; break;
                case R.id.navigation_test: nextFragmentNb = 2; break;
            }

            currentCardNb = deck.getCards().size()-1;

            configureNextCard();
            return true;
        }
    };

    private void configureNextFab(){
        nextFab.setVisibility(View.INVISIBLE);
    }


    //---------------------------
    //CONFIGURATION OF FRAGMENTS
    //--------------------------


    private void configureNextCard(){

        boolean cardLeftInTheDeck = false;
        for (int i = 0; i < deck.getCards().size(); i++) {
            if(deck.getCards().get(i).getStatus() != 2){
                cardLeftInTheDeck = true;
            }
        }

        if(!cardLeftInTheDeck){
            deckFinishedAlertDialog();
        }else{

            //Configure next card number
            while(currentCardNb >= 0 && deck.getCards().get(currentCardNb).getStatus() == 2){
                currentCardNb--;
            }

            if (currentCardNb >= 0){
                configureNextQuestion();
                configureNextAnswer();
                configureNextFab();
            }else{
                if(nextFragmentNb != 2){
                    endOfDeckAlertDialog();
                }else{
                    currentCardNb = deck.getCards().size() - 1;
                    configureNextCard();
                }
            }
        }
    }

    private void configureNextQuestion(){
        String question = deck.getCards().get(currentCardNb).getKey();
        questionTextView.setText(question);
    }

    private void configureNextAnswer(){

        //Configure Args
        Bundle args = new Bundle();
        args.putString(DeckWithCards.ANSWER_KEY, deck.getCards().get(currentCardNb).getValue());

        //Alternative answers for Quiz
        if(nextFragmentNb == 1){
            ArrayList<String> alternatives = new ArrayList<>();
            int i = 0;

            while(alternatives.size()<3){
                if(i != currentCardNb){
                    alternatives.add(deck.getCards().get(i).getValue());
                }
                i++;
            }
            args.putStringArrayList(DeckWithCards.ALTERNATIVES_KEY, alternatives);
        }

        //Configure Fragment
        Fragment fragment = null;

        switch (nextFragmentNb){
            case 0: fragment = new MemorizeFragment(); break;
            case 1: fragment = new QuizFragment(); break;
            case 2: fragment = new TestFragment(); break;
        }

        //Replace current Fragment
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_base_empty_frame, fragment);
        transaction.commit();
    }



    //---------------------------
    //EVENTS
    //--------------------------

    private void endOfDeckAlertDialog(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(LearnActivity.this);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        switch (nextFragmentNb){
            case 0 :
                builder .setMessage("Bravo! Vous avez fini la pile! Prêt pour le quiz?")
                        .setPositiveButton("Pas encore!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                currentCardNb = deck.getCards().size()-1;
                                nextFragmentNb = 0;
                                configureNextCard();
                                }
                        })
                        .setNegativeButton("Oui!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                currentCardNb = deck.getCards().size()-1;
                                nextFragmentNb = 1;
                                configureNextCard();
                                }
                        })
                        .create()
                        .show();
                break;
            case 1 :
                builder .setMessage("Bravo! Vous avez fini la pile! Prêt pour le test?")
                        .setPositiveButton("Pas encore", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                currentCardNb = deck.getCards().size()-1;
                                nextFragmentNb = 1;
                                configureNextCard();
                                }
                        })
                        .setNegativeButton("Oui!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                currentCardNb = deck.getCards().size()-1;
                                nextFragmentNb = 2;
                                configureNextCard();
                                }
                        })
                        .create()
                        .show();
                break;
        }
    }

    private void deckFinishedAlertDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(LearnActivity.this);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        builder .setMessage("Bravo! Vous avez fini la pile!")
                .setPositiveButton("Retour à l'accueil ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentMain);
                    }
                })
                .create()
                .show();
    }


    //-----------------------------------
    // ACTIONS
    //-----------------------------------

    @OnClick(R.id.next_fab)
    public void seeNextCard(View v) {
        Log.e(TAG, "fab clicked");
        this.configureNextCard();
    }


    //-----------------------------------
    // CALLBACKS
    //-----------------------------------

    @Override
    public void onMemorizeAnswer() {
        Log.e(TAG, "memorize answered");
        nextFragmentNb = 0;
        currentCardNb--;

        nextFab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onQuizAnswer(boolean success) {
        Log.e(TAG, "quiz answered");
        if(success){
            deck.getCards().get(currentCardNb).setStatus(1);
        }
        Log.e(TAG, "card status = " + deck.getCards().get(currentCardNb).getStatus());

        nextFragmentNb = 1;
        currentCardNb--;

        nextFab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTestAnswer(boolean success) {
        Card currentCard = deck.getCards().get(currentCardNb);

        if(success){
            currentCard.setStatus(2);
            currentCard.setDeckId(deck.getId());
            this.deckViewModel.updateCard(currentCard);
        }else{
            currentCard.setStatus(0);
        }

        nextFragmentNb = 2;
        currentCardNb--;

        nextFab.setVisibility(View.VISIBLE);
    }


    //-----------------------------------
    // ACCESS TO DATABASE
    //-----------------------------------

    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.deckViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeckViewModel.class);
        this.deckViewModel.init();
    }

}
