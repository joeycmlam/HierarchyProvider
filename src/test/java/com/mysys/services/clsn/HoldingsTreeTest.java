package com.mysys.services.clsn;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class HoldingsTreeTest {


    @Autowired
    private HoldingsTree holdingsTree = new HoldingsTree();


    @Before
    public void setUp() throws Exception {
        this.holdingsTree.addHolding(new Holding("0005.HK",new BigDecimal("400")));
        this.holdingsTree.addHolding(new Holding("TOPX1",new BigDecimal("88000")));
        this.holdingsTree.addHolding(new Holding("0002.HK",new BigDecimal("5000")));
        this.holdingsTree.addHolding(new Holding("HSI1",new BigDecimal("3000")));
        this.holdingsTree.addHolding(new Holding("SONY.JP",new BigDecimal("2000")));
        this.holdingsTree.addHolding(new Holding("TTO.JP",new BigDecimal("2000")));
        this.holdingsTree.addHolding(new Holding("AMZN.US",new BigDecimal("2300")));
        this.holdingsTree.addHolding(new Holding("BARC.LN",new BigDecimal("2300")));
        this.holdingsTree.addHolding(new Holding("FB.US",new BigDecimal("22300")));

    }


    @Test
    public void testPrintTree() {
        this.holdingsTree.printTree();
        Assert.assertTrue(true);

    }

}