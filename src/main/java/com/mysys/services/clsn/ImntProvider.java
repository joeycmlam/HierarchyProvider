package com.mysys.services.clsn;

import com.mysys.services.util.DataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("InstrumentProvider")
public class ImntProvider implements iImntProvider{


    final private Map<String, Instrument> imnts = new ConcurrentHashMap<>();


    @Override
    public Instrument getInstrument(String stockCode) {
        return this.imnts.get(stockCode);
    }


    @Override
    public void addInstrument(Instrument instrument)  {

        try {
            Instrument aImnt = (Instrument) instrument.clone();
            this.imnts.put(instrument.getStockCode(), aImnt);
        } catch (Exception e) {
//            throw e;
        }


    }

    @Override
    public int size() {
        return this.imnts.size();
    }

    public void loadData(String fileName) {
        List<Instrument> data = DataLoader.loadObjectList(Instrument.class, fileName);
        data.forEach(imnt -> addInstrument(imnt));
    }
}
