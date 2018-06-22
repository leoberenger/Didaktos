package fr.didaktos.didaktos.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import fr.didaktos.didaktos.database.dao.CardDAO;
import fr.didaktos.didaktos.database.dao.DeckDAO;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;

public class CardDataRepository {

    private final CardDAO cardDAO;

    public CardDataRepository(CardDAO cDAO){
        this.cardDAO = cDAO;
    }

    // CREATE
    public void createCard(Card c){
        cardDAO.insertCard(c);
    }

    // READ
    public LiveData<List<Card>> getAllCards(){
        return this.cardDAO.getAllCards();
    }

    public LiveData<Card> getCard(long cardId){
        return this.cardDAO.getCard(cardId);
    }

    // UPDATE
    public void updateCard(Card c){
        cardDAO.updateCard(c);
    }

}
