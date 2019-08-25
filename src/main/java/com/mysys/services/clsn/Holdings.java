package com.mysys.services.clsn;



import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Component("HoldingsProvider")
public class Holdings implements iHoldings  {
    final private List<Holding> lstHolding = new ArrayList<>();


    @Override
    public void addHolding(Holding holding) {
        this.lstHolding.add(holding);
    }



    @Override
    public Predicate<Holding> filterByHashCode(Integer hashCode) {
        return p -> p.hashCode() == hashCode;
    }

    @Override
    public Predicate<Holding> filterByStockCode(String stockCode) {
        return p -> p.getStockCode().equals(stockCode);
    }


    @Override
    public Holding getHolding(Predicate<Holding> predicate) {
        return lstHolding.stream().filter(predicate)
                .findAny().orElse(null);
    }


    public BigDecimal getMVByStockCode(String stockCode) {
        return this.lstHolding.stream().filter(h -> h.getStockCode().equals(stockCode))
                .map(mv -> mv.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public String toString() {
        final StringBuilder rtnValue = new StringBuilder();
        rtnValue.append("holding count = [").append(lstHolding.size()).append("]");
        return rtnValue.toString();

    }
}
