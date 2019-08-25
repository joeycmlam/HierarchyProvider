package com.mysys.services.clsn;

import com.sun.org.apache.bcel.internal.generic.INEG;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ImntProviderTest {


    @Autowired
    private ImntProvider imnt;

    @Before
    public void setUp() throws Exception {
        this.imnt.loadData("data/imnt.csv");
    }

    @Test
    public void TestAddInstrument() {
        final Instrument expImnt = new Instrument();
        expImnt.setStockCode("SDIV");
        expImnt.setAssetType("ETF");
        expImnt.setCountry("US");
        expImnt.setSector("UNKNOWN");

        this.imnt.addInstrument(expImnt);

        expImnt.setSector("YYYY");
        Instrument actValue = this.imnt.getInstrument("SDIV");
        Assert.assertFalse(expImnt.equals(actValue));
    }

    @Test
    public void TestgetInstrumentHSBC() {
        final Instrument actValue = this.imnt.getInstrument("0005.HK");
        Assert.assertNotNull(actValue);

    }

    @Test
    public void TestgetInstrumentNotFound() {
        final Instrument actValue = this.imnt.getInstrument("xxxx");
        Assert.assertNull(actValue);
    }


}