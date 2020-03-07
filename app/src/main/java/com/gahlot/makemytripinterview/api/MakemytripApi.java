package com.gahlot.makemytripinterview.api;

import androidx.annotation.Keep;

import com.gahlot.makemytripinterview.model.Root;
import com.gahlot.makemytripinterview.model.Variants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MakemytripApi {

    String BASE_URL = "https://api.myjson.com/";

    @GET("bins/3b0u2/")
    Call<Root> getRoot();

}
