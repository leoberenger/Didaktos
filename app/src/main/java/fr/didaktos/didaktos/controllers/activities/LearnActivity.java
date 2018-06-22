package fr.didaktos.didaktos.controllers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

public class LearnActivity extends AppCompatActivity {

    private String TAG = "Learn Activity";

    private TextView mTextMessage;
    private DeckWithCards deck;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_memorize:
                    mTextMessage.setText(R.string.title_memorize);
                    return true;
                case R.id.navigation_quiz:
                    mTextMessage.setText(R.string.title_quiz);
                    return true;
                case R.id.navigation_test:
                    mTextMessage.setText(R.string.title_test);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        //if Edit, retrieve property
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);
            Log.e(TAG, "title = " + deck.getTitle());
            Log.e(TAG, "cards size = " + deck.getCards().size());


        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
