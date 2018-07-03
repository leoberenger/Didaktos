package fr.didaktos.didaktos.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Card implements Parcelable {
    @PrimaryKey private long id;
    private long deckId;
    private String key;
    private String value;
    private int nextWorkDate;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Card(long id, long deckId, String key, String value, int nextWorkDate) {
        this.id = id;
        this.deckId = deckId;
        this.key = key;
        this.value = value;
        this.nextWorkDate = nextWorkDate;
    }

    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public long getDeckId() {
        return deckId;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public int getNextWorkDate() {
        return nextWorkDate;
    }

    //---------------------------
    //SETTERS
    //--------------------------

    public void setId(long id) {
        this.id = id;
    }
    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setNextWorkDate(int nextWorkDate) {
        this.nextWorkDate = nextWorkDate;
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
        out.writeLong(deckId);
        out.writeString(key);
        out.writeString(value);
        out.writeInt(nextWorkDate);
    }

    public static final Creator<Card> CREATOR
            = new Creator<Card>() {

        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    private Card(Parcel in) {
        id = in.readLong();
        deckId = in.readLong();
        key = in.readString();
        value = in.readString();
        nextWorkDate = in.readInt();
    }
}
