package com.mysys.services.clsn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TreeTest {

    private static HoldingTree t = new HoldingTree();

    @Before
    public void setup() {
        t.addHolding("HK", "0001.HK", new BigDecimal("10"));
        t.addHolding("HK", "0003.HK", new BigDecimal("20"));
        t.addHolding("HK", "0005.HK", new BigDecimal("30"));
        t.addHolding("JP", "SONY", new BigDecimal("50"));
    }

    @Test
    public void findHoldingByStockFoundTest() {

        final BigDecimal actMv = t.findHoldingByStockCode("0001.HK");
        final BigDecimal expMv = new BigDecimal("10");
        Assert.assertEquals(expMv, actMv);
    }

    @Test
    public void findHoldingByStockFoundAnotherStockTest() {

        final BigDecimal actMv = t.findHoldingByStockCode("0005.HK");
        final BigDecimal expMv = new BigDecimal("30");
        Assert.assertEquals(expMv, actMv);
    }

    @Test
    public void findHoldingByStockNotFoundTest() {
        final BigDecimal actMv = t.findHoldingByStockCode("2800.HK");
        final BigDecimal expMv = new BigDecimal("0");
        Assert.assertEquals(expMv, actMv);
    }

    @Test
    public void findHoldingByStockFoundLevel2Test() {
        final BigDecimal actMv = t.findHoldingByStockCode("SONY");
        final BigDecimal expMv = new BigDecimal("50");
        Assert.assertEquals(expMv, actMv);
    }


    @Test
    public void printAllTest() {
        this.t.printTree();
    }



}