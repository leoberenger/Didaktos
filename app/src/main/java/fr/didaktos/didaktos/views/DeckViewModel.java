package fr.didaktos.didaktos.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.Executor;

import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.repositories.CardDataRepository;
import fr.didaktos.didaktos.repositories.DeckDataRepository;

public class DeckViewModel extends ViewModel {

    //REPOSITORIES
    private final DeckDataRepository deckDataSource;
    private final CardDataRepository cardDataSource;
    private final Executor executor;

    //DATA
    @Nullable
    private LiveData<List<Deck>> decks;
    @Nullable
    LiveData<List<Card>> cards;

    public DeckViewModel(DeckDataRepository deckDataSource, CardDataRepository cardDataSource, Executor executor){
        this.deckDataSource = deckDataSource;
        this.cardDataSource = cardDataSource;
        this.executor = executor;
    }

    public void init(){
        if(this.decks != null){return;}
            decks = deckDataSource.getAllDecks();
    }

    //------------------------
    // FOR DECKS
    //------------------------

    public LiveData<List<Deck>> getAllDecks(){
        return deckDataSource.getAllDecks();
    }

    public LiveData<Deck> getDeck(long deckId){
        return deckDataSource.getDeck(deckId);
    }

    public LiveData<Deck> getDeckWithTitle(String title){
        return deckDataSource.getDeckWithTitle(title);
    }

    public LiveData<DeckWithCards> getDeckWithCards(long deckId){
        return deckDataSource.getDeckWithCards(deckId);
    }

    public LiveData<List<DeckWithCards>> getDecksWithCards(){
        return deckDataSource.getDecksWithCards();
    }

    public void createDeck (Deck d){
        executor.execute(()-> deckDataSource.createDeck(d));
    }

    public void updateDeck (final Deck d){
        executor.execute(() -> deckDataSource.updateDeck(d));
    }

    //------------------------
    // FOR CARDS
    //------------------------

    public LiveData<List<Card>> getAllCards(){
        return cardDataSource.getAllCards();
    }

    public LiveData<Card> getCard(long cardId){
        return cardDataSource.getCard(cardId);
    }

    public void createCard (Card c){
        executor.execute(()-> cardDataSource.createCard(c));
    }

    public void updateCard (final Card c){
        executor.execute(() -> cardDataSource.updateCard(c));
    }
}
