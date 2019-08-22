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
        Node aNode = this.root;

        while (aNode != null) {
            final Integer intLevel = aNode.getLevel();
            System.out.print(StringUtils.repeat(' ', intLevel));
            System.out.println(aNode.toString());


            aNode.getHoldings().forEach(h ->
                    System.out.println(String.format("%s holding: %s",
                            StringUtils.repeat(' ', intLevel+1),
                            this.holdings.getHolding(this.holdings.filterByHashCode(h.hashCode())))));

            aNode = aNode.traverseInOrder();



        }
    }


}
