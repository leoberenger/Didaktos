package fr.didaktos.didaktos.models;

import android.arch.persistence.room.Relation;

import java.util.List;

public class DeckWithCards {
    public int id;
    public String name;
    @Relation(parentColumn = "id", entityColumn = "deckId")
    public List<Card> cards;

    public DeckWithCards(){}

    public DeckWithCards(int id, String name, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
