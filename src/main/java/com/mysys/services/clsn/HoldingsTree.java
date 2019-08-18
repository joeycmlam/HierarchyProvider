package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HoldingsTree {

    final private Holdings holdings = new Holdings();
    final private Node root = new Node("Total Portfolio");


    public void addHolding(Holding holding) {
        this.holdings.add(holding);
        Node aNode = this.root.addChild(holding.getAssetType());
        aNode = aNode.addChild(holding.getCountry());
        aNode.addHolding(holding);

    }


    public void printTree() {
        Node aNode = this.root;

        while (aNode != null) {
            System.out.println(aNode.toString());

            aNode.getHoldings().forEach(h -> System.out.println(
                    String.format("holding: %s",
                            this.holdings.getHolding(h.hashCode()))));

            aNode = aNode.traverseInOrder();

        }
    }


}
