package com.seriabov.fintecharch.presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.seriabov.fintecharch.data.model.ApiResponse;
import com.seriabov.fintecharch.data.repository.CoinsRepository;
import com.seriabov.fintecharch.data.repository.CoinsRepositoryImpl;

public class ListCoinsViewModel extends ViewModel {
    private MediatorLiveData<ApiResponse> apiResponse;
    private CoinsRepository coinsRepository;

    public ListCoinsViewModel() {
        apiResponse = new MediatorLiveData<>();
        coinsRepository = new CoinsRepositoryImpl();
    }

    public MediatorLiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    public LiveData<ApiResponse> loadCoinsInfo() {
        apiResponse.addSource(coinsRepository.getCoins(),
                apiResponse1 -> apiResponse.setValue(apiResponse1));
        return apiResponse;
    }


}
