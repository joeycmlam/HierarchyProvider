package com.mysys.services.clsn;

import java.util.ArrayList;
import java.util.List;

public class NodeExample<T> {
    private T data = null;

    private List<NodeExample<T>> children = new ArrayList<>();

    private NodeExample<T> parent = null;

    public NodeExample(T data) {
        this.data = data;
    }

    public NodeExample<T> addChild(NodeExample<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<NodeExample<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<NodeExample<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(NodeExample<T> parent) {
        this.parent = parent;
    }

    public NodeExample<T> getParent() {
        return parent;
    }


    public static <T> void printTree(NodeExample<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

}
