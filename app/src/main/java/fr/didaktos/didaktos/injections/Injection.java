package fr.didaktos.didaktos.injections;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.didaktos.didaktos.database.DidaktosDatabase;
import fr.didaktos.didaktos.repositories.DeckDataRepository;

public class Injection {
    public static DeckDataRepository provideDeckDataSource(Context context) {
        DidaktosDatabase database = DidaktosDatabase.getInstance(context);
        return new DeckDataRepository(database.deckDao());
    }


    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        DeckDataRepository dataSourceDeck = provideDeckDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceDeck, executor);
    }
}
