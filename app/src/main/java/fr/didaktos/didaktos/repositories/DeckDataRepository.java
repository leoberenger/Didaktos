package fr.didaktos.didaktos.repositories;

import android.arch.lifecycle.LiveData;
import java.util.List;

import fr.didaktos.didaktos.database.dao.DeckDAO;
import fr.didaktos.didaktos.models.Deck;

public class DeckDataRepository {

    private final DeckDAO deckDAO;

    public DeckDataRepository(DeckDAO dDAO){
        this.deckDAO = dDAO;
    }

    // CREATE
    public void createDeck(Deck d){
        deckDAO.insertDeck(d);
    }

    // READ
    public LiveData<List<Deck>> getAllDecks(){
        return this.deckDAO.getAllDecks();
    }

    public LiveData<Deck> getDeck(long deckId){
        return this.deckDAO.getDeck(deckId);
    }

    // UPDATE
    public void updateDeck(Deck d){
        deckDAO.updateDeck(d);
    }

}
