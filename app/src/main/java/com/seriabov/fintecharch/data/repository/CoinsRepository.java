package com.seriabov.fintecharch.data.repository;

import android.arch.lifecycle.LiveData;

import com.seriabov.fintecharch.data.model.ApiResponse;

public interface CoinsRepository {
    LiveData<ApiResponse> getCoins();
}
