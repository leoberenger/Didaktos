package fr.didaktos.didaktos.models;

import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DeckWithCards  implements Parcelable {

    public static String DECKS_KEY = "DECKS";
    public static String DECK_KEY = "DECK";

    private long id;
    private String name;
    @Relation(parentColumn = "id", entityColumn = "deckId")
    private List<Card> cards;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public DeckWithCards(){}

    public DeckWithCards(int id, String name, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.cards = cards;
    }

    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Card> getCards() {
        return cards;
    }

    //---------------------------
    //SETTERS
    //--------------------------

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    //---------------------------
    //PARCELABLE METHODS
    //--------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(name);
    }

    public static final Creator<DeckWithCards> CREATOR
            = new Creator<DeckWithCards>() {

        public DeckWithCards createFromParcel(Parcel in) {
            return new DeckWithCards(in);
        }

        public DeckWithCards[] newArray(int size) {
            return new DeckWithCards[size];
        }
    };

    private DeckWithCards(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }
}
