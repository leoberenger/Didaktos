package fr.didaktos.didaktos.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import fr.didaktos.didaktos.database.dao.DeckDAO;
import fr.didaktos.didaktos.models.Deck;

@Database(entities = {Deck.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DidaktosDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile DidaktosDatabase INSTANCE;

    // --- DAO ---
    public abstract DeckDAO deckDao();


    // --- INSTANCE ---
    public static DidaktosDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (DidaktosDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DidaktosDatabase.class, "MyDatabase.db")
                            //.addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };
    }

    public DidaktosDatabase() {
    }

}
