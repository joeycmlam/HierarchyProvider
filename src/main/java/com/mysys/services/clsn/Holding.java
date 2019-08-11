package com.mysys.services.clsn;

import java.math.BigDecimal;


public class Holding {
    private String stockCode;
    private BigDecimal mv;
    private String country;
    private String assetType;

    public enum EnumGroupField {
        COUNTRY,
        ASSET_TYPE,
        REGION
    }

    public Holding(String name,  String country, String assetType, BigDecimal mv) {
        this.stockCode = name;
        this.country = country;
        this.assetType = assetType;
        this.mv = mv;
    }

    public String getRegion() {
        String region=this.country;
        switch (this.country) {
            case "HK"   : region = "Asia"; break;
            case "JP"   : region = "Asia"; break;
            case "KR"   : region = "Asia"; break;
            case "US"   : region = "US"; break;
            case "Itay" : region = "Europe"; break;
            case "UK"   : region = "Europe"; break;
        }
        return region;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getAssetType() {
        return assetType;
    }

    public BigDecimal getMv() {
        return mv;
    }

    public void setMv(BigDecimal mv) {
        this.mv = mv;
    }

    public String getCountry() {
        return country;
    }

    public String getField(EnumGroupField field) {
        switch (field) {
            case COUNTRY:
                return this.getCountry();
            case ASSET_TYPE:
                return this.getAssetType();
            case REGION:
                return this.getRegion();
            default:
                return "";
        }
    }
}

