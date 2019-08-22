package com.mysys.services.clsn;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(stockCode);

    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj) return false;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        return this.stockCode.equalsIgnoreCase((String) obj);
    }
}
