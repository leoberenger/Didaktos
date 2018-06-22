package fr.didaktos.didaktos.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Deck{ //implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Deck() { }

    public Deck(String name) {
        this.name = name;
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


    //---------------------------
    //SETTERS
    //--------------------------

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
/*

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
    */
}
