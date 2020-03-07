package com.gahlot.makemytripinterview.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gahlot.makemytripinterview.model.Root;

public interface OKCallback {

    void onSuccess(MutableLiveData<Root> liveData);

    void onFailure(String result);

}
