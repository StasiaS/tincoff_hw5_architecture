package com.seriabov.fintecharch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.seriabov.fintecharch.AppDelegate;
import com.seriabov.fintecharch.data.model.ApiResponse;
import com.seriabov.fintecharch.data.model.CoinInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinsRepositoryImpl implements CoinsRepository {


    @Override
    public LiveData<ApiResponse> getCoins() {
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();

        AppDelegate.getApiService()
                .getCoinsList()
                .enqueue(new Callback<List<CoinInfo>>() {
                    @Override
                    public void onResponse(Call<List<CoinInfo>> call, Response<List<CoinInfo>> response) {
                        liveData.setValue(new ApiResponse(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<CoinInfo>> call, Throwable t) {
                        liveData.setValue(new ApiResponse(t));
                    }
                });
        return liveData;
    }
}
