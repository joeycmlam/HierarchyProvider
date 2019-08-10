package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    private int level;
    private int seq;
    private String name;
    private Node parent;
    private List<Holding> values;
    private List<Node> children;


    public Node(String name) {
        this.level = 0;
        this.seq = 0;
        this.name = name;
        this.parent = null;
        this.values = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
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
            Node aChild = this.children.stream().filter(c -> c.getName().equals(country)).findAny().orElse(null);
            if (aChild == null) {
                aChild = new Node(country);
                aChild.level = this.level + 1;
                this.children.add(aChild);
                aChild.addChild(stockCode, country, mv);
                aChild.setParent(this);

            } else {
                aChild.addChild(stockCode, country, mv);
            }
        }
    }


    public List<Holding> findChildByStockCode(String stockCode) {
        List<Holding> lstHolding = this.values.stream().filter(h -> h.getStockCode().equals(stockCode)).collect(Collectors.toList());
        if (lstHolding.size() == 0 && this.children.size() > 0) {
            for (Node c : this.children) {
                lstHolding = c.findChildByStockCode(stockCode);
                if (lstHolding.size() > 0) {
                    break;
                }
            }

        }
        return lstHolding;

    }


    public List<Holding> findChildByCountry(String country) {
        List<Holding> lstValue = new ArrayList<Holding>();


        if (country.equals(this.name)) {
            lstValue = this.values;
        } else {
            for (Node c : this.children) {
                lstValue = c.findChildByCountry(country);
                if (lstValue.size() > 0) {
                    break;
                }
            }

        }
        return lstValue;
    }



    public BigDecimal getMV() {

        BigDecimal totalMV =  this.values.stream().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (this.children.size() > 0) {


            for (Node c : this.children) {
                for (Holding h : c.values) {
                    totalMV = totalMV.add(h.getMv());
                }
            }

        }

        return totalMV;


    }


    public void printTree() {

        System.out.println(String.format("Level %d: [%s] [%09.4f]", this.level, this.name, this.getMV()));

        if (this.values.size() > 0) {
            this.values.forEach(holding -> System.out.println(String.format("holding: [%s] [%09.4f]", holding.getStockCode(), holding.getMv())));

        }
        if (this.children != null && this.children.size() > 0) {
//            this.children.printTree();
            this.children.forEach(c -> c.printTree());
        }
    }



}

