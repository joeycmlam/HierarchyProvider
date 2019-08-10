package com.mysys.services.clsn;

import java.math.BigDecimal;

public class Holding {
    private String name;
    private BigDecimal mv;
    private String country;

    public Holding(String name, String country, BigDecimal mv) {
        this.name = name;
        this.country = country;
        this.mv = mv;
    }

    public String getName() {
        return name;
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

