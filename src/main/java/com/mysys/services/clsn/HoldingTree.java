package com.mysys.services.clsn;


import java.math.BigDecimal;
import java.util.List;

public class HoldingTree {

    private Node root;

    public HoldingTree() {
        root = new Node("TotalPortfolio");
    }

    public void addHolding(String country, String stockCode, BigDecimal mv) {
        this.root.addChild(stockCode, country, mv);
    }

    public void printTree() {
        this.root.printTree();
    }


    public BigDecimal getMVByStockCode(String stockCode) {
        List<Holding> lstHolding = this.root.findChildByStockCode(stockCode);
        if (lstHolding == null || lstHolding.size() == 0) {
            return BigDecimal.ZERO;
        } else {
            return lstHolding.stream().parallel().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }


    public BigDecimal getMVByCountry(String country) {
        List<Holding> lstHolding = this.root.findChildByCountry(country);
        BigDecimal rtnValue = BigDecimal.ZERO;
        if (lstHolding.size() > 0) {

            rtnValue = lstHolding.stream().parallel().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return rtnValue;



    }
}
