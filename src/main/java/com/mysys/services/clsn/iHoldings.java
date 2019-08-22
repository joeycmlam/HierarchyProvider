package com.mysys.services.clsn;

import java.util.List;
import java.util.function.Predicate;

public interface iHoldings {
    public void addHolding(Holding holding);
    public Holding getHolding(Predicate<Holding> predicate);
    public Predicate<Holding> filterByStockCode(String stockCode);
    public Predicate<Holding> filterByHashCode(Integer hashCode);
}
