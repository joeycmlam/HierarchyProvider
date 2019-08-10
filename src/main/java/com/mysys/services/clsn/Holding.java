package com.mysys.services.clsn;

import java.math.BigDecimal;

public class Holding {
    private String stockCode;
    private BigDecimal mv;
    private String country;

    public Holding(String name, String country, BigDecimal mv) {
        this.stockCode = name;
        this.country = country;
        this.mv = mv;
    }

    public String getStockCode() {
        return stockCode;
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
}

