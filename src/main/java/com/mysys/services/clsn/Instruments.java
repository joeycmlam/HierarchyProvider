package com.mysys.services.clsn;

import java.util.HashMap;
import java.util.Map;

public class Instruments {
    private Map<String, iImnt> lstImnt = new HashMap<>();

    public void add(iImnt aImnt) {
        this.lstImnt.put(aImnt.getStockCode(), aImnt);
    }

    public iImnt get(String stockCode) {
        return this.lstImnt.get(stockCode);

    }
}
