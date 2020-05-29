package com.company;

public class Avl {
    private Node headNode;
    private int size;

    public Avl() {
        headNode = null;
        size = 0;
    }

    public int balanceFact(Node node) {
        return node.getLeftChild().getHeight() - node.getRightChild().getHeight();
    }

    public void fixHeight(Node node) {
        int heightOfLeftChild = node.getLeftChild().getHeight();
        int heightOfRightChild = node.getRightChild().getHeight();

        node.setHeight(Math.max(heightOfLeftChild, heightOfRightChild) + 1);
    }
    // Правый поворот вокруг p
    private Node rotateRight (Node p) {
        Node q = p.getLeftChild();
        p.setLeftChild(q.getRightChild());
        q.setRightChild(p);
        fixHeight(p);
        fixHeight(q);
        return q;
    }
    // Левый поворот вокруг q
    private Node rotateLeft(Node q) {
        Node p = q.getRightChild();
        q.setRightChild(p.getLeftChild());
        p.setLeftChild(q);
        fixHeight(q);
        fixHeight(p);
        return p;
    }
    // балансировка узла p
    private Node balance(Node p) {
        fixHeight(p);
        if (balanceFact(p) == 2) {
            if (balanceFact(p.getRightChild()) < 0) p.setRightChild(rotateRight(p.getRightChild()));
            return rotateLeft(p);
        }
        if (balanceFact(p) == -2) {
            if (balanceFact(p.getLeftChild()) > 0) p.setLeftChild(rotateLeft(p.getLeftChild()));
            return rotateRight(p);
        }
        return p; // балансировка не нужна
    }

    // вставка ключа с определенным родителем(корень)
    private Node insert(Node parent, int key) {
        if (parent == null) return new Node(parent.getParent(), key);
        if (key < parent.getKey()) parent.setLeftChild(insert(parent.getLeftChild(), key));
        else parent.setRightChild(insert(parent.getRightChild(), key));
        return balance(parent);
    }
    // поиск узла с минимальным ключем в дереве p
    private Node findMin(Node p) {
        return p.getLeftChild() == null ? p : findMin(p.getLeftChild());
    }
    // удаление узла с минимальным ключем из дерева p
    private Node removeMin(Node p) {
        if (p.getLeftChild() == null) return p.getRightChild();
        p.setLeftChild(removeMin(p.getLeftChild()));
        return balance(p);
    }
    // удаление ключа из дерева
    public Node remove(Node parent, int key) {
        if (parent == null) return null;
        if (key < parent.getKey()) parent.setLeftChild(remove(parent.getLeftChild(), key));
        else if (key > parent.getKey()) parent.setRightChild(remove(parent.getRightChild(), key));
        else {
            Node q = parent.getLeftChild();
            Node p = parent.getRightChild();
            if (p == null) return q;
            Node min = findMin(p);
            min.setRightChild(removeMin(p));
            min.setLeftChild(q);
            return balance(min);     // при выходе балансируем
        }
        return balance(parent);
    }

}
