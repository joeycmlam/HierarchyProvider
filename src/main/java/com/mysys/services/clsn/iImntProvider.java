package com.mysys.services.clsn;

public interface iImntProvider {

    public Instrument getInstrument(String stockCode);

    public void addInstrument(Instrument instrument);

    public int size();

}
