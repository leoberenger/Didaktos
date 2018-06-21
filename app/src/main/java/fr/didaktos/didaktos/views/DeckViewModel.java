package fr.didaktos.didaktos.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.Executor;

import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.repositories.DeckDataRepository;

public class DeckViewModel extends ViewModel {

    //REPOSITORIES
    private final DeckDataRepository deckDataSource;
    private final Executor executor;

    //DATA
    @Nullable
    private LiveData<List<Deck>> decks;

    public DeckViewModel(DeckDataRepository deckDataSource, Executor executor){
        this.deckDataSource = deckDataSource;
        this.executor = executor;
    }

    public void init(){
        if(this.decks != null){return;}
            decks = deckDataSource.getAllDecks();
    }

    //------------------------
    // FOR PROPERTY
    //------------------------

    public LiveData<List<Deck>> getAllDecks(){
        return deckDataSource.getAllDecks();
    }


    public LiveData<Deck> getDeck(long deckId){
        return deckDataSource.getDeck(deckId);
    }

    public void createDeck (Deck d){
        executor.execute(()->{
            deckDataSource.createDeck(d);
        });
    }

    public void updateDeck (final Deck d){
        executor.execute(() -> {
            deckDataSource.updateDeck(d);
        });
    }
}
