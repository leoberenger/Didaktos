package fr.didaktos.didaktos.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.controllers.fragments.DetailFragment;
import fr.didaktos.didaktos.controllers.fragments.MainFragment;
import fr.didaktos.didaktos.database.DataGenerator;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.views.DeckViewModel;

public class MainActivity  extends AppCompatActivity
        implements MainFragment.OnDecksListSelectedListener
        {

    private String TAG = "MainActivity";

    private long deckId = -1;
    private DeckViewModel deckViewModel;
    private ArrayList<DeckWithCards> deckArrayList;
    private DeckWithCards deck;

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureToolbar();

        this.configureViewModel();
        //this.populateDatabase();
        Stetho.initializeWithDefaults(this);

        this.deckViewModel.getDecksWithCards().observe(this, this::configureAndShowMainFragment);
    }

    //-----------------------------------
    // NAVIGATION
    //-----------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menu_search:
                //Intent intentSearch = new Intent(this, SearchActivity.class);
                //startActivity(intentSearch);
                return true;

            case R.id.menu_add:
                Intent intentCreate = new Intent(this, EditionActivity.class);
                startActivity(intentCreate);
                return true;

            case R.id.menu_edit:
                Intent intentEdit = new Intent(this, EditionActivity.class);
                intentEdit.putExtra(DeckWithCards.DECK_KEY, deck);
                startActivity(intentEdit);
                return true;

            case R.id.menu_stats:
                Intent intentStats = new Intent(getApplicationContext(), StatsActivity.class );
                intentStats.putParcelableArrayListExtra(DeckWithCards.DECKS_KEY, deckArrayList);
                startActivity(intentStats);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //-----------------------------------
    // CONFIGURATION
    //-----------------------------------

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configureAndShowMainFragment(List<DeckWithCards> decks){

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.decks_recycler_view);

        Bundle bundle = new Bundle();
        deckArrayList = new ArrayList<>(decks);
        bundle.putParcelableArrayList(DeckWithCards.DECKS_KEY, deckArrayList);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_main_fragment, mainFragment)
                    .commit();
        }

        //Show by default 1st item
        if (decks.size() != 0){
            deckId = decks.get(0).getId();
            getDeckToShow(deckId);
        }else{
            Toast.makeText(this, "No property corresponding", Toast.LENGTH_LONG).show();
        }
    }

    private void configureAndShowDetailFragment (DeckWithCards deck){

        this.deck = deck;

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_detail_layout);

        Bundle bundle = new Bundle();
        bundle.putParcelable(DeckWithCards.DECK_KEY, deck);

        if (fragment == null) {
            fragment = new DetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_detail_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    //-----------------------------------
    // CALLBACKS
    //-----------------------------------

    @Override
    public void onDeckSelected(long deckId) {
        this.deckId = deckId;
        getDeckToShow(deckId);
    }

    //-----------------------------------
    // RETRIEVE DATA FROM DB
    //-----------------------------------

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

    private void getDeckToShow(long deckId){
        this.deckViewModel.getDeckWithCards(deckId).observe(this, this::configureAndShowDetailFragment);
    }
}
