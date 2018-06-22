package fr.didaktos.didaktos.controllers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.fragments.learn.MemorizeFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.QuizFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.TestFragment;
import fr.didaktos.didaktos.models.DeckWithCards;

public class LearnActivity extends AppCompatActivity {

    private String TAG = "Learn Activity";

    private DeckWithCards deck;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_memorize:
                    fragment = new MemorizeFragment();
                    break;
                case R.id.navigation_quiz:
                    fragment = new QuizFragment();
                    break;
                case R.id.navigation_test:
                    fragment = new TestFragment();
                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_learn_frame_layout, fragment);
            transaction.commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        //retrieve deck
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);
            Log.e(TAG, "title = " + deck.getTitle());
            Log.e(TAG, "cards size = " + deck.getCards().size());
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_learn_frame_layout,  new MemorizeFragment());
        transaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

}
