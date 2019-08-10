package com.mysys.services.clsn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TreeTest {

    private static HoldingTree t = new HoldingTree();
    public static boolean started = false;


    @Before
    public void setup() {
        if (this.started) {
            return;
        }

        this.started = true;
        // HK ==> 60
        t.addHolding("HK", "Equity", "0001.HK", new BigDecimal("10"));
        t.addHolding("HK", "Equity", "0003.HK", new BigDecimal("20"));
        t.addHolding("HK", "Equity","0005.HK", new BigDecimal("30"));

        t.addHolding("JP", "Equity","SONY", new BigDecimal("50000"));
        t.addHolding("JP", "Futures","TOPIX", new BigDecimal("72300"));

        t.addHolding("UK", "Equity","BACY", new BigDecimal("50"));
        // US ==> 10450
        t.addHolding("US","Equity", "AMAZ", new BigDecimal("8900"));
        t.addHolding("US", "Equity","AIA", new BigDecimal("1550"));

    }


    @Test
    public void findHoldingByCountryTest() {
        BigDecimal actValue = t.getMVByCountry("HK");
        BigDecimal expValue = new BigDecimal("60");
        Assert.assertEquals(expValue, actValue);

    }

    @Test
    public void findHoldingByCountryNotFoundTest() {
        BigDecimal actValue = t.getMVByCountry("XX");
        BigDecimal expValue = new BigDecimal("0");
        Assert.assertEquals(expValue, actValue);
    }


    @Test
    public void findHoldingByStockFoundTest() {

        final BigDecimal actMv = t.getMVByStockCode("0001.HK");
        final BigDecimal expMv = new BigDecimal("10");
        Assert.assertEquals(expMv, actMv);
    }

    @Test
    public void findHoldingByStockFoundAnotherStockTest() {

        final BigDecimal actMv = t.getMVByStockCode("0005.HK");
        final BigDecimal expMv = new BigDecimal("30");
        Assert.assertEquals(expMv, actMv);
    }

    @Test
    public void findHoldingByStockNotFoundTest() {
        final BigDecimal actMv = t.getMVByStockCode("2800.HK");
        final BigDecimal expMv = new BigDecimal("0");
        Assert.assertEquals(expMv, actMv);
    }

    @Test
    public void findHoldingByStockFoundLevel2Test() {
        final BigDecimal actMv = t.getMVByStockCode("SONY");
        final BigDecimal expMv = new BigDecimal("50000");
        Assert.assertEquals(expMv, actMv);
    }


    @Test
    public void printAllTest() {
        this.t.printTree();
    }


}