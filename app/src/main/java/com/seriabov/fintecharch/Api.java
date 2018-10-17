package com.seriabov.fintecharch;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Api {

    @GET("ticker/?limit=20")
    Call<List<CoinInfo>> getCoinsList();
}
