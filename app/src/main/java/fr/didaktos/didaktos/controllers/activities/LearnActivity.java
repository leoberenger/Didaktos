package fr.didaktos.didaktos.controllers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.fragments.learn.MemorizeFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.MemorizeViewPagerFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.QuizFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.TestFragment;
import fr.didaktos.didaktos.models.DeckWithCards;

public class LearnActivity extends AppCompatActivity {

    private String TAG = "Learn Activity";

    private DeckWithCards deck;

    @BindView(R.id.activity_learn_toolbar) Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        ButterKnife.bind(this);

        //retrieve deck
        if(getIntent().getParcelableExtra(DeckWithCards.DECK_KEY) != null){
            deck = getIntent().getParcelableExtra(DeckWithCards.DECK_KEY);
        }

        this.configureToolbar();
        this.replaceCurrentFragment(new MemorizeViewPagerFragment());
        this.configureBottomNavigation();
    }

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void replaceCurrentFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DeckWithCards.DECK_KEY, deck);
        fragment.setArguments(bundle);
        transaction.replace(R.id.activity_learn_frame_layout, fragment)
                    .commit();
    }

    private void configureBottomNavigation(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = new Fragment();

            switch (item.getItemId()) {
                case R.id.navigation_memorize:
                    fragment = new MemorizeViewPagerFragment();
                    break;
                case R.id.navigation_quiz:
                    fragment = new QuizFragment();
                    break;
                case R.id.navigation_test:
                    fragment = new TestFragment();
                    break;
            }

            replaceCurrentFragment(fragment);
            return true;
        }
    };
}
