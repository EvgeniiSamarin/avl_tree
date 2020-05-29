package com.company;

public class Node {
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private int height;
    private int key;


    public Node( int key) {
        this(null, key);
        this.height = 1;
    }

    public Node getParent() {
        return parent;
    }

    public Node(Node parent, int key) {
        this.parent = parent;
        this.key = key;
        leftChild  = null;
        rightChild = null;
        this.height = parent.getHeight() + 1;
    }




    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
