package fr.didaktos.didaktos.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;


import java.util.List;

import fr.didaktos.didaktos.models.Deck;
import fr.didaktos.didaktos.models.DeckWithCards;

@Dao
public interface DeckDAO {

    @Query("SELECT * FROM Deck")
    LiveData<List<Deck>> getAllDecks();

    @Query("SELECT * FROM Deck")
    Cursor getDecksWithCursor();

    @Query("SELECT * FROM Deck WHERE id = :deckId")
    LiveData<Deck> getDeck(long deckId);

    @Query("SELECT * FROM Deck WHERE id = :deckId")
    LiveData<DeckWithCards> getDeckWithCards(long deckId);

    @Query("SELECT * FROM Deck")
    LiveData<List<DeckWithCards>> getDecksWithCards();

    @Insert
    long insertDeck(Deck deck);

    @Update
    int updateDeck(Deck deck);

}
