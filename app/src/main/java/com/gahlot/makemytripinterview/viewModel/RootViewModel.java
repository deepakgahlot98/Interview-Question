package com.gahlot.makemytripinterview.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gahlot.makemytripinterview.model.Root;
import com.gahlot.makemytripinterview.model.Variations;
import com.gahlot.makemytripinterview.repository.PizzaRepository;

import java.util.List;

public class RootViewModel extends AndroidViewModel {

    private PizzaRepository pizzaRepository;
    private LiveData<Root> liveData;
    private LiveData<List<Variations>> variations_LiveData;

    public RootViewModel(@NonNull Application application) {
        super(application);
        pizzaRepository = new PizzaRepository();
    }

    public LiveData getRoot() {
        liveData = pizzaRepository.getRoot();
        return liveData;
    }

//    public LiveData getVarations() {
//        variations_LiveData = pizzaRepository.getVariations();
//        return variations_LiveData;
//    }
}
