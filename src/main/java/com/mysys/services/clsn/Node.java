package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Node {

    private int level;
    private int seq;
    private String name;
    private List<Holding> values;
    private Node parent;
    private Node next;

    public Node(String name) {
        this.level = 0;
        this.seq = 0;
        this.name = name;
        this.parent = null;
        this.next = null;
        this.values = new ArrayList<>();
    }




    public void setParent(Node parent) {
        this.parent = parent;
        this.level = parent.level + 1;
    }

    public void addChild(String stockCode, String country, BigDecimal mv) {
        if (country.equals(this.name)) {
            Holding aHolding = new Holding(stockCode, country, mv);
            this.values.add(aHolding);
        } else {
            if (this.next == null) {
                Node aNewNode = new Node(country);
                aNewNode.addChild(stockCode, country, mv);
                aNewNode.setParent(this);

                this.next = aNewNode;
            } else {
                this.next.addChild(stockCode, country, mv);
            }
        }

    }

    private Node findNode(String country) {
        if (this.next == null) {
            return null;
        } else {
            if (country.equals(this.next.name)) {
                return this.next;
            } else {
                return this.next.findNode(country);
            }
        }
    }


    public Holding findChild(String stockCode) {
        Holding aHolding = values.stream().filter(c -> stockCode.equals(c.getName())).findAny().orElse(null);
        if (aHolding == null && this.next != null) {
            aHolding = this.next.findChild(stockCode);
        }
        return aHolding;

    }

    public BigDecimal getMV() {

        BigDecimal totalMV =  this.values.stream().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (this.next != null) {
            totalMV = totalMV.add(this.next.getMV());
        }

        return totalMV;


    }

    public void printTree() {

        System.out.println(String.format("Level %d: [%s] [%09.4f]", this.level, this.name, this.getMV()));

        if (this.values.size() > 0) {
            this.values.forEach(holding -> System.out.println(String.format("holding: [%s] [%09.4f]", holding.getName(), holding.getMv())));

        }
        if (this.next != null) {
            this.next.printTree();
        }
    }
}

