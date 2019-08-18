package com.mysys.services.clsn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NodeHolding {

    private int level;
    private int seq;
    private String name;
    private NodeHolding parent;
    private List<Holding> values;
    private List<NodeHolding> children;


    public NodeHolding(String name) {
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

    public void setParent(NodeHolding parent) {
        this.parent = parent;
        this.level = parent.level + 1;
    }

    private NodeHolding findNode(String dataPoint) {
        NodeHolding aNode = null;
        if (!this.name.equals(dataPoint)) {
            for (NodeHolding c : this.children) {
                if (c.getName().equals(dataPoint)) {
                    aNode = c;
                    break;
                } else {
                    c.findNode(dataPoint);
                }
            }
        }
        return aNode;
    }


    public NodeHolding addChild(Holding aHolding, List<Holding.EnumGroupField> lstGroupBy) {
        String dataPoint;
        NodeHolding aParent = this;
        NodeHolding aNode = null;
        for (Holding.EnumGroupField groupBy : lstGroupBy) {
            dataPoint = aHolding.getField(groupBy);
            aNode = this.addNode(aHolding, groupBy, aParent);
            if (aNode.level == lstGroupBy.size()) {
                aNode = aNode.addChild(aHolding, groupBy);
            }

            aParent = aNode; //prepare for next groupBy

        }
        return aNode;
    }


    public NodeHolding addChild(Holding aHolding, Holding.EnumGroupField groupBy) {
        NodeHolding aNode = this.addChild(aHolding, groupBy, this);
        return aNode;
    }

    public NodeHolding addChild(Holding aHolding, Holding.EnumGroupField groupBy, NodeHolding aParent) {
        NodeHolding aChild;
        if (aHolding.getField(groupBy).equals(this.name)) {
            this.values.add(aHolding);
            aChild = this;
        } else {
            aChild = this.addNode(aHolding, groupBy, aParent);
            aChild.addChild(aHolding, groupBy, aParent);
            aChild.setParent(aParent);
        }
        return aChild;
    }

    private NodeHolding addNode(Holding aHolding, Holding.EnumGroupField groupBy, NodeHolding parent) {

        NodeHolding aNode = parent.findNode(aHolding.getField(groupBy));
        if (aNode == null) {
            aNode = new NodeHolding(aHolding.getField(groupBy));
            aNode.level = parent.level + 1;
            parent.children.add(aNode);
            aNode.setParent(parent);
        }

        return aNode;
    }


    public List<Holding> findChildByStockCode(String stockCode) {
        List<Holding> lstHolding = this.values.stream().filter(h -> h.getStockCode().equals(stockCode)).collect(Collectors.toList());
        if (lstHolding.size() == 0 && this.children.size() > 0) {
            for (NodeHolding c : this.children) {
                lstHolding = c.findChildByStockCode(stockCode);
                if (lstHolding.size() > 0) {
                    break;
                }
            }

        }
        return lstHolding;

    }


    public List<Holding> findChildByGroupLevel(String groupLevel) {
        List<Holding> lstValue = new ArrayList<Holding>();

        if (groupLevel.equals(this.name)) {
            lstValue = this.values;
            for (NodeHolding c : this.children) {
                lstValue.addAll(c.values);
            }

        } else {
            for (NodeHolding c : this.children) {
                lstValue = c.findChildByGroupLevel(groupLevel);
                if (lstValue.size() > 0) {
                    break;
                }
            }
        }


        return lstValue;
    }


    public BigDecimal getMV() {

        BigDecimal totalMV = this.values.stream().map(h -> h.getMv()).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (this.children.size() > 0) {


            for (NodeHolding c : this.children) {
                for (Holding h : c.values) {
                    totalMV = totalMV.add(h.getMv());
                }
            }

        }

        return totalMV;


    }


    public void printTree() {

        if (this.parent == null) {
            System.out.println(String.format("Level %d: [%s] [%09.4f]", this.level, this.name, this.getMV()));
        } else {
            System.out.println(String.format("Level %d: [%s] [%s] [%09.4f]", this.level, this.parent.getName(), this.name, this.getMV()));
        }


        if (this.values.size() > 0) {
            this.values.forEach(holding -> System.out.println(String.format("holding:[%s] [%09.4f]", holding.getStockCode(), holding.getMv())));

        }
        if (this.children != null && this.children.size() > 0) {
            this.children.forEach(c -> c.printTree());
        }
    }


}

