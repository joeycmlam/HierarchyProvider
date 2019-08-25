package com.mysys.services.clsn;

public class Instrument implements Cloneable {
    private String stockCode;
    private String assetType;
    private String country;
    private String sector;

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockCode() {
        return stockCode;
    }


    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object obj) {
        Instrument aImnt = (Instrument) obj;

        return this.stockCode.equals(aImnt.stockCode) &&
                this.country.equals(aImnt.country) &&
                this.assetType.equals(aImnt.assetType) &&
                this.sector.equals(aImnt.sector);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
