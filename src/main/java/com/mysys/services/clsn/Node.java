package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Node {

    private int level;
    private String name;
    private List<Child> values;
    private Node parent;
    private Node next;

    public Node(String name) {
        this.name = name;
        this.parent = null;
        this.next = null;
        this.values = new ArrayList<>();
    }


    public void addChild(String stockCode, String country, BigDecimal mv) {
        if (country.equals(this.name)) {
            Child aChild = new Child(stockCode, country, mv);
            this.values.add(aChild);
        } else {
            if (this.next == null) {
                Node aNewNode = new Node(country);
                aNewNode.addChild(stockCode, country, mv);
                aNewNode.parent = this;
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


    public Child findChild(String stockCode) {
        Child aChild = values.stream().filter(c -> stockCode.equals(c.getName())).findAny().orElse(null);
        if (aChild == null && this.next != null) {
            aChild = this.next.findChild(stockCode);
        }
        return aChild;

    }
}

