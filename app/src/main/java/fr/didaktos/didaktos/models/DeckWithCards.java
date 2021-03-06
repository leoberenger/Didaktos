package fr.didaktos.didaktos.models;

import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class DeckWithCards  implements Parcelable {

    public static final String DECKS_KEY = "DECKS";
    public static final String DECK_KEY = "DECK";
    public static final String NEXT_DECK_ID_KEY = "NEXT__DECK_ID";
    public static final String NEXT_CARD_ID_KEY = "NEXT_CARD_ID";
    public static String CARD_NB_KEY = "CARD_NB";
    public static String ANSWER_KEY = "ANSWER";
    public static String ALTERNATIVES_KEY = "ALTERNATIVES";
    public static String VALUE_KEY = "VALUE";
    public static String CARDTOWORK_KEY = "HAS_CARDTOWORK";


    private long id;
    private String topic;
    private String title;
    private String description;
    private String imgUrl;

    @Relation(parentColumn = "id", entityColumn = "deckId")
    private List<Card> cards;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public DeckWithCards(){}

    public DeckWithCards(Deck deck, List<Card> cards){
        this.id = deck.getId();
        this.topic = deck.getTopic();
        this.title = deck.getTitle();
        this.description = deck.getDescription();
        this.imgUrl = deck.getImgUrl();
        this.cards = cards;
    }

    public DeckWithCards(long id, String topic, String title, String description, String imgUrl, List<Card> cards) {
        this.id = id;
        this.topic = topic;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.cards = cards;
    }


    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public String getTopic() {
        return topic;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getImgUrl() {
        return imgUrl;
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
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        out.writeString(topic);
        out.writeString(title);
        out.writeString(description);
        out.writeString(imgUrl);
        out.writeList(cards);
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
        topic = in.readString();
        title = in.readString();
        description = in.readString();
        imgUrl = in.readString();
        cards = new ArrayList<>();
        in.readList(cards, Card.class.getClassLoader());
    }
}