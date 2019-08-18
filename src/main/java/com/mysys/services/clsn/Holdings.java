package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.*;

public class Holdings  {
    private List<Holding> lstHolding = new ArrayList<>();

    public void add(Holding aHolding) {
        this.lstHolding.add(aHolding);
    }


    public Holding getHolding(Integer holdingKey) {
        Holding h = lstHolding.stream().filter(holding -> holding.hashCode() == holdingKey).findAny().orElse(null);
        return h;
    }

    public Holding getHoldingByStockCode(String stockCode) {
        return this.lstHolding.get(Objects.hash(stockCode));
    }

    public BigDecimal getMVByStockCode(String stockCode) {
        return this.lstHolding.stream().filter(h -> h.getStockCode().equals(stockCode))
                .map(mv -> mv.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
