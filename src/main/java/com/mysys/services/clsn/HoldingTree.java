package com.mysys.services.clsn;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HoldingTree {

    private Node root;


    public HoldingTree() {
        root = new Node("TotalPortfolio");
    }


    public void addHolding(String country, String assetType, String stockCode, BigDecimal mv, List<Holding.EnumGroupField> lstGroupBy) {
        Holding h = new Holding(stockCode, country, assetType, mv);
        this.root.addChild(h, lstGroupBy);

    }

    public void addHolding(String country, String assetType, String stockCode, BigDecimal mv) {
        Holding h = new Holding(stockCode, country, assetType, mv);
        this.root.addChild(h, Holding.EnumGroupField.COUNTRY);
//        this.root.addChild(h, Holding.EnumGroupField.ASSET_TYPE);


    }

    public void printTree() {
        this.root.printTree();
    }


    public BigDecimal getMVByStockCode(String stockCode) {
        List<Holding> lstHolding = this.root.findChildByStockCode(stockCode);
        BigDecimal rtnValue = BigDecimal.ZERO;
        if (lstHolding != null && lstHolding.size() > 0) {

            rtnValue = lstHolding.stream().parallel().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return rtnValue;
    }


    public BigDecimal getMVByCountry(String country) {
        List<Holding> lstHolding = this.root.findChildByGroupLevel(country);
        BigDecimal rtnValue = BigDecimal.ZERO;
        if (lstHolding != null && lstHolding.size() > 0) {

            rtnValue = lstHolding.stream().parallel().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return rtnValue;
    }

}
