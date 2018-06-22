package fr.didaktos.didaktos.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Card {
    @PrimaryKey(autoGenerate = true) long id;
    int deckId;
    String key;
    String value;

    public Card(int deckId, String key, String value) {
        this.id = id;
        this.deckId = deckId;
        this.key = key;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
