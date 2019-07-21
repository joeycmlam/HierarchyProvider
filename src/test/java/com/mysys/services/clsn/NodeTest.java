package com.mysys.services.clsn;

import org.junit.Assert;
import org.junit.Test;


public class NodeTest {

    @Test
    public void TestNodeAsStock() {


    }

    @Test
    public void TestNodeAsString() {
        Node<String> root = createTree();
        root.printTree(root, " ");

        Assert.assertTrue(true);
    }

    private static Node<String> createTree() {
        Node<String> root = new Node<>("root");

        Node<String> node1 = root.addChild(new Node<String>("node 1"));

        Node<String> node11 = node1.addChild(new Node<String>("node 11"));
        Node<String> node111 = node11.addChild(new Node<String>("node 111"));
        Node<String> node112 = node11.addChild(new Node<String>("node 112"));

        Node<String> node12 = node1.addChild(new Node<String>("node 12"));

        Node<String> node2 = root.addChild(new Node<String>("node 2"));

        Node<String> node21 = node2.addChild(new Node<String>("node 21"));
        Node<String> node211 = node2.addChild(new Node<String>("node 22"));
        return root;
    }


}