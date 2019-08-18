package com.mysys.services.clsn;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

public class HoldingsTreeTest {

    private HoldingsTree holdingsTree = new HoldingsTree();

    @Before
    public void setUp() throws Exception {
        this.holdingsTree.addHolding(new Holding("0005.HK",new BigDecimal("400")));
        this.holdingsTree.addHolding(new Holding("TOPX1",new BigDecimal("88000")));
        this.holdingsTree.addHolding(new Holding("0002.HK",new BigDecimal("5000")));
        this.holdingsTree.addHolding(new Holding("HSI1",new BigDecimal("3000")));
        this.holdingsTree.addHolding(new Holding("SONY.JP",new BigDecimal("2000")));

    }

    @Ignore
    public void testGetHolding() {
//        Holding expValue = new Holding("0005.HK", new BigDecimal("5000"));
//        Holding actValue = this.holdingsTree.getHoldingByStockCode("0005.HK");
//        Assert.assertTrue(expValue.equals(actValue));
    }

    @Test
    public void testPrintTree() {
        this.holdingsTree.printTree();
    }

}