package fr.didaktos.didaktos.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Deck {

    @PrimaryKey
    private long id;
    private String topic;
    private String title;
    private String description;
    private String imgUrl;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Deck() { }

    public Deck(long id, String topic, String title, String description, String imgUrl) {
        this.id = id;
        this.topic = topic;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
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

}
