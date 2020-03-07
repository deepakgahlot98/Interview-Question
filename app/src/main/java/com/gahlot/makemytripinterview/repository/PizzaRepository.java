package com.gahlot.makemytripinterview.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gahlot.makemytripinterview.api.APIClient;
import com.gahlot.makemytripinterview.api.MakemytripApi;
import com.gahlot.makemytripinterview.api.OKCallback;
import com.gahlot.makemytripinterview.model.Root;
import com.gahlot.makemytripinterview.model.Variations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PizzaRepository {

    private static final String TAG = "PizzaRepository";
    private static PizzaRepository pizzaRepository;
    private MutableLiveData<List<Variations>> variations;

    public static PizzaRepository getInstance(){
        if (pizzaRepository == null){
            pizzaRepository = new PizzaRepository();
        }
        return pizzaRepository;
    }

    private MakemytripApi makemytripApi;


    public PizzaRepository() {
        makemytripApi = APIClient.cteateService(MakemytripApi.class);
    }

    public LiveData<Root> getRoot() {
        MutableLiveData<Root> root = new MutableLiveData<>();
        Call<Root> call = makemytripApi.getRoot();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.i(TAG, "onResponse: " + response.body().toString());
                root.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
        while (root == null) {
            try {
                Thread.sleep(1000);
                Log.e(TAG, "getRoot: Thread is sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return root;
    }

//    public LiveData<List<Variations>> getVariations() {
//        MutableLiveData<List<Variations>> list_variations = null;
//        list_variations.setValue(getRoot().getValue().getVariants().getVariant_groups().get(0).getVariations());
//        return list_variations;
//    }





}
