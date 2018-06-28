package fr.didaktos.didaktos.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.fragments.MainFragment;
import fr.didaktos.didaktos.controllers.fragments.StatsFragment;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.DeckViewModel;

public class StatsActivity extends AppCompatActivity
        implements StatsFragment.OnDecksListSelectedListener  {

    private String TAG = "Stats Activity";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    private ArrayList<DeckWithCards> deckArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ButterKnife.bind(this);

        deckArrayList = getIntent().getParcelableArrayListExtra(DeckWithCards.DECKS_KEY);

        this.configureToolbar();
        this.configureAndShowStatsFragment();
    }

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configureAndShowStatsFragment(){

        StatsFragment fragment = (StatsFragment) getSupportFragmentManager().findFragmentById(R.id.decks_recycler_view);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DeckWithCards.DECKS_KEY, deckArrayList);

        if (fragment == null) {
            fragment = new StatsFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_stats_fragment, fragment)
                    .commit();
        }
    }

    @Override
    public void onDeckSelected(DeckWithCards deck) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra(DeckWithCards.DECK_KEY, deck);
        startActivity(intent);
    }
}
