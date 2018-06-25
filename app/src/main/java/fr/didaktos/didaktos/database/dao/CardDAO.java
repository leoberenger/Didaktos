package fr.didaktos.didaktos.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import fr.didaktos.didaktos.models.Card;

@Dao
public interface CardDAO {

    @Query("SELECT * FROM Card")
    LiveData<List<Card>> getAllCards();

    @Query("SELECT * FROM Card")
    Cursor getCardsWithCursor();

    @Query("SELECT * FROM Card WHERE id = :cardId")
    LiveData<Card> getCard(long cardId);

    @Insert
    long insertCard(Card card);

    @Update
    int updateCard(Card card);

}
