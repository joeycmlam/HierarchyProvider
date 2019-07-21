package com.mysys.services.clsn;

public class Stock {
    private String stockCode;
    private String country;
    private String msciSector;


    public Stock(String stockCode) {
        this.stockCode = stockCode;
    }


    public String getStockCode() {
        return stockCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMsciSector() {
        return msciSector;
    }

    public void setMsciSector(String msciSector) {
        this.msciSector = msciSector;
    }
}
