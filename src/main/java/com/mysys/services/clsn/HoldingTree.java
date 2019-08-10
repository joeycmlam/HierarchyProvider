package com.mysys.services.clsn;


import java.math.BigDecimal;

public class HoldingTree {

    private Node root;

    public HoldingTree() {
        root = new Node("TotalPortfolio");
    }

    public void addHolding(String country, String stockCode, BigDecimal mv) {
        this.root.addChild(stockCode, country, mv);
    }

    public BigDecimal findHoldingByStockCode(String stockCode) {
        Holding aHolding = this.root.findChild(stockCode);
        if (aHolding == null) {
            return BigDecimal.ZERO;
        } else {
            return aHolding.getMv();
        }
    }

    public void printTree() {
        this.root.printTree();
    }
}
