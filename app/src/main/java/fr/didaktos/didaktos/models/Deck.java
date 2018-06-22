package fr.didaktos.didaktos.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Deck {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String imgUrl;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Deck() { }

    public Deck(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
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

}
