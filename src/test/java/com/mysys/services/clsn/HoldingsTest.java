package com.mysys.services.clsn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class HoldingsTest {

    private Holdings holdings = new Holdings();

    @Before
    public void Setup() {

        this.holdings.add(new Holding("0005.HK", "HK", "Equity", new BigDecimal("5000")));
    }


    @Test
    public void testWhenSearchaHolding() {
        Holding actualValue = this.holdings.getHoldingByStockCode("0005.HK");
        BigDecimal expValue = new BigDecimal("5000");
        Assert.assertEquals(expValue, actualValue.getMv());
    }

    @Test
    public void testWhenSearchHoldingNotExist() {
        Holding actualValue = this.holdings.getHoldingByStockCode("0005.XX");
        BigDecimal expValue = null;
        Assert.assertEquals(expValue, null);
    }
}