package com.seriabov.fintecharch.data.model;

import java.util.List;

public class ApiResponse {
    private List<CoinInfo> coinInfos;
    private Throwable error;

    public ApiResponse(List<CoinInfo> coinsInfo) {
        this.coinInfos = coinsInfo;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.coinInfos = null;
        this.error = error;
    }

    public List<CoinInfo> getCoinInfos() {
        return coinInfos;
    }

    public Throwable getError() {
        return error;
    }
}
