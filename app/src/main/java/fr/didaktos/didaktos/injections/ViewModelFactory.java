package fr.didaktos.didaktos.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;

import fr.didaktos.didaktos.repositories.CardDataRepository;
import fr.didaktos.didaktos.repositories.DeckDataRepository;
import fr.didaktos.didaktos.views.DeckViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory{

    private final DeckDataRepository deckDataSource;
    private final CardDataRepository cardDataSource;
    private final Executor executor;

    public ViewModelFactory(DeckDataRepository deckDataSource, CardDataRepository cardDataSource, Executor executor){
        this.deckDataSource = deckDataSource;
        this.cardDataSource = cardDataSource;
        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DeckViewModel.class)){
            return (T) new DeckViewModel(deckDataSource, cardDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
