package fr.didaktos.didaktos.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Card implements Parcelable {
    @PrimaryKey(autoGenerate = true) private long id;
    private int deckId;
    private String key;
    private String value;
    private int status;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Card(int deckId, String key, String value, int status) {
        this.deckId = deckId;
        this.key = key;
        this.value = value;
        this.status = status;
    }

    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public int getDeckId() {
        return deckId;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public int getStatus() {
        return status;
    }

    //---------------------------
    //SETTERS
    //--------------------------

    public void setId(long id) {
        this.id = id;
    }
    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setStatus(int status) {
        this.status = status;
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
        out.writeString(key);
        out.writeString(value);
        out.writeInt(status);
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
        key = in.readString();
        value = in.readString();
        status = in.readInt();
    }
}
