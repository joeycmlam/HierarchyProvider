package com.mysys.services.clsn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class HoldingsTest {

    @Autowired
    public iHoldings holdings;

    @Before
    public void Setup() {

        this.holdings.addHolding(new Holding("0005.HK", "HK", "Equity", new BigDecimal("5000")));
    }

    @Test
    public void testDependencyInjection() {
        Assert.assertThat(holdings, instanceOf(Holdings.class));
    }

    @Test
    public void testWhenSearchaHolding() {
        Holding actualValue = this.holdings.getHolding(this.holdings.filterByStockCode("0005.HK"));
        BigDecimal expValue = new BigDecimal("5000");
        Assert.assertEquals(expValue, actualValue.getMv());
    }

    @Test
    public void testWhenSearchHoldingNotExist() {
        Holding actualValue = this.holdings.getHolding(this.holdings.filterByStockCode("0005.XX"));
        BigDecimal expValue = null;
        Assert.assertEquals(expValue, null);
    }
}