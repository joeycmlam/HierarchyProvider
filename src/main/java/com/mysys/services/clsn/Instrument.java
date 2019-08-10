package com.mysys.services.clsn;

public class Instrument implements iImnt {
    private String stockCode;
    private String country;
    private String assetType;

    public Instrument(String stockCode, String country, String assetType) {
        this.stockCode = stockCode;
        this.country = country;
        this.assetType = assetType;
    }

    @Override
    public String getStockCode() {
        return this.stockCode;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getAssetType() {
        return this.assetType;
    }
}
