package fr.didaktos.didaktos.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Deck implements Parcelable {

    public static String DECK_KEY = "deck";
    public static String DECKS_KEY = "decks";
    public static String DECK_ID = "DECK_ID";

    public static String NAME_KEY = "name";
    public static String IMG_URL_KEY = "imgUrl";
    public static String NB_CARDS_KEY = "nbCards";


    @PrimaryKey(autoGenerate = true) private long id;
    private String name;
    private String imgUrl;
    private int nbCards;
    private String[] keys;
    private String[] values;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Deck() { }

    public Deck(String name, String imgUrl, int nbCards, String[] keys, String[] values) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.nbCards = nbCards;
        this.keys = keys;
        this.values = values;
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
    public String getImgUrl() {
        return imgUrl;
    }
    public int getNbCards() {
        return nbCards;
    }
    public String[] getKeys() {
        return keys;
    }
    public String[] getValues() {
        return values;
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
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
    }
    public void setKeys(String[] keys) {
        this.keys = keys;
    }
    public void setValues(String[] values) {
        this.values = values;
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
        out.writeString(imgUrl);
        out.writeInt(nbCards);
        out.writeStringArray(keys);
        out.writeStringArray(values);
    }

    public static final Creator<Deck> CREATOR
            = new Creator<Deck>() {

        public Deck createFromParcel(Parcel in) {
            return new Deck(in);
        }

        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };

    private Deck(Parcel in) {
        id = in.readLong();
        name = in.readString();
        imgUrl = in.readString();
        nbCards = in.readInt();
        in.readStringArray(keys);
        in.readStringArray(values);
    }
}
