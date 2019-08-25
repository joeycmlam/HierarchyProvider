package com.mysys.services.clsn;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("HoldingsTreeProvider")
public class HoldingsTree {

    @Autowired
    private iHoldings holdings;

    final private Node root = new Node("Total Portfolio");


    public void addHolding(Holding holding) {
        this.holdings.addHolding(holding);
        Node aNode = this.root.addChild(holding.getAssetType());
        aNode = aNode.addChild(holding.getRegion());
        aNode = aNode.addChild(holding.getCountry());
        aNode.addHolding(holding);

    }


    public void printTree() {
        System.out.println(this.buildTree());

    }

    public String buildTree() {
        final StringBuilder buff = new StringBuilder();
        Node aNode = this.root;

        while (aNode != null) {
            final Integer intLevel = aNode.getLevel();
            buff.append(StringUtils.repeat(' ', intLevel));
            buff.append(aNode.toString()).append("\n");


            aNode.getHoldings().forEach(h ->
                    buff.append(
                            StringUtils.repeat(' ', intLevel+1)
                    ).append(
                            this.holdings.getHolding(this.holdings.filterByHashCode(h.hashCode()))
                    ).append("\n")
            );

            aNode = aNode.traverseInOrder();
        }
        return buff.toString();
    }

}
