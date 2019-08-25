package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.Objects;


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

    public Holding(String stockCode, BigDecimal mv) {
        this.stockCode = stockCode;
        this.mv = mv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stockCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj) return false;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Holding h = (Holding) obj;
        return this.stockCode.equalsIgnoreCase((String) obj);
    }

    public Holding(String name, String country, String assetType, BigDecimal mv) {
        this.stockCode = name;
        this.country = country;
        this.assetType = assetType;
        this.mv = mv;
    }



    public String getRegion() {
        String region = "UNKNOWN";
        switch (this.getCountry()) {
            case "HK":
                region = "Asia";
                break;
            case "JP":
                region = "Asia";
                break;
            case "KR":
                region = "Asia";
                break;
            case "US":
                region = "US";
                break;
            case "Itay":
                region = "Europe";
                break;
            case "UK":
                region = "Europe";
                break;
        }
        return region;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getAssetType() {
        String rtnValue = this.assetType;
        if (this.assetType == null) {
            switch (this.stockCode) {
                case "0005.HK":
                case "0002.HK":
                case "SONY.JP":
                case "FB.US":
                case "TTO.JP":
                case "AMZN.US":
                case "BARC.LN":
                    rtnValue = "Equity";
                    break;
                case "HSI1":
                case "TOPX1":
                    rtnValue = "Future";
                    break;

            }
        }

        return rtnValue;
    }

    public BigDecimal getMv() {
        return mv;
    }

    public void setMv(BigDecimal mv) {
        this.mv = mv;
    }

    public String getCountry() {
        String rtnValue = this.country;

        if (rtnValue == null) {

            switch (this.stockCode) {
                case "0005.HK":
                case "0002.HK":
                case "HSI1":
                    rtnValue = "HK";
                    break;
                case "TOPX1":
                case "SONY.JP":
                case "TTO.JP":
                    rtnValue = "JP";
                    break;
                case "FB.US":
                case "AMZN.US":
                    rtnValue = "US";
                    break;
                case "BARC.LN":
                    rtnValue= "UK";
                    break;
            }
        }
        return rtnValue;
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

    @Override
    public String toString() {

        final StringBuilder rtnValue = new StringBuilder();
        rtnValue.append("holding: ");
        rtnValue.append("[").append(this.stockCode).append("] ");
        rtnValue.append("MV [").append(this.getMv()).append("]");


        return rtnValue.toString();
    }
}

