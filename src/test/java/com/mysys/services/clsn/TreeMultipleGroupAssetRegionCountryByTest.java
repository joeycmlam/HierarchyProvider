package com.mysys.services.clsn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TreeMultipleGroupAssetRegionCountryByTest {

    private static HoldingTree t = new HoldingTree();
    public static boolean started = false;


    @Before
    public void setup() {
        if (this.started) {
            return;
        }

        final List<Holding.EnumGroupField> lstGroupBy = new ArrayList<>();
        lstGroupBy.add(Holding.EnumGroupField.ASSET_TYPE);
        lstGroupBy.add(Holding.EnumGroupField.REGION);
        lstGroupBy.add(Holding.EnumGroupField.COUNTRY);


        this.started = true;
        // HK ==> 60
        t.addHolding("HK", "Equity", "0001.HK", new BigDecimal("10"), lstGroupBy);
        t.addHolding("HK", "Equity", "0003.HK", new BigDecimal("20"), lstGroupBy);
        t.addHolding("HK", "Equity","0005.HK", new BigDecimal("30"), lstGroupBy);

        t.addHolding("JP", "Equity","SONY", new BigDecimal("50000"), lstGroupBy);
        t.addHolding("JP", "Future","TOPIX", new BigDecimal("72300"), lstGroupBy);

        t.addHolding("KR", "Equity","SAMSUNG", new BigDecimal("7000"), lstGroupBy);

        t.addHolding("Itay", "Equity","BBCA", new BigDecimal("90"), lstGroupBy);
        t.addHolding("UK", "Equity","BACY", new BigDecimal("50"), lstGroupBy);
        // US ==> 10450
        t.addHolding("US","Equity", "AMAZ", new BigDecimal("8900"), lstGroupBy);
        t.addHolding("US", "Equity","AIA", new BigDecimal("1550"), lstGroupBy);

    }


    @Test
    public void findHoldingByRegionTest() {
        BigDecimal actValue = t.getMVByCountry("Asia");
        BigDecimal expValue = new BigDecimal("122360");
        Assert.assertEquals(expValue, actValue);

    }

    @Test
    public void findHoldingByCountryTest() {
        BigDecimal actValue = t.getMVByCountry("HK");
        BigDecimal expValue = new BigDecimal("60");
        Assert.assertEquals(expValue, actValue);

    }

    @Test
    public void findHoldingByCountryJapanTest() {
        BigDecimal actValue = t.getMVByCountry("JP");
        BigDecimal expValue = new BigDecimal("122300");
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