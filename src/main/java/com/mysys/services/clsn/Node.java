package com.mysys.services.clsn;


import java.util.ArrayList;
import java.util.List;


public class Node {

    private int level;
    private String value;
    private Node next;
    private Node prev;
    private Node parent;
    private Node child;
    private List<Integer> holdings = new ArrayList<>();


    public Node(String value) {
        this.value = value;
    }

    public List<Integer> getHoldings() {
        return holdings;
    }

    public int getLevel() {
        return level;
    }

    public String getValue() {
        return value;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Node getNext() {
        return next;
    }

    public Node getParent() {
        return parent;
    }

    public Node traverseInOrder() {
        Node aNode = this.getChild();
        if (aNode == null ) {
            aNode = this.getNext();
            if (aNode == null) {
                aNode = this.getParent();
                if (aNode != null) {
                    aNode = this.getParent().getNext();
                }
            }
        }
        return aNode;
    }

    public Node addNode(String groupBy) {
        Node aNode = this.findNode(groupBy);

        if (aNode == null) {
            aNode = new Node(groupBy);
            aNode.prev = this;
            aNode.parent = this.parent;
            aNode.level = this.level;
            this.next = aNode;
        }

        return aNode;
    }

    public Node addChild(String groupBy) {
        Node aNode = this.getChild();
        if (aNode == null) {
            aNode = new Node(groupBy);
            aNode.level = this.level + 1;
            aNode.parent = this;
            this.setChild(aNode);
        } else {
            aNode = aNode.addNode(groupBy);
        }

        return aNode;
    }

    private Node findNode(String groupBy) {
        Node aNode = null;

        if (this.getValue().equals(groupBy)) {
            aNode = this;
        } else if (this.getNext() != null) {
            aNode = this.getNext().findNode(groupBy);
        }
        return aNode;
    }

    public void addHolding(Holding holding) {
        this.holdings.add(holding.hashCode());
    }



    @Override
    public String toString() {
        StringBuilder rtnValue = new StringBuilder();

        rtnValue.append("Node: ");
        rtnValue.append("level [").append(this.level).append("] ");
        rtnValue.append("[").append(this.value).append("] ");


        return rtnValue.toString();
    }


}
