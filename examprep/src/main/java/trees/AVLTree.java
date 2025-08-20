package main.java.trees;

public class AVLTree<K extends Comparable<K>, V> extends Tree<K, V> {

    public AVLTree() {
        super();
    }

    public AVLTree(Node<K, V> root) {
        insertNode(root);
    }

    @Override
    public void insertNode(Node<K, V> node) {
        setRootNode(insert(getRootNode(), node));
    }

    private Node<K, V> insert(Node<K, V> root, Node<K, V> node) {
        if (root == null) {
            return node;
        }
        int cmp = node.getKey().compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeft(insert(root.getLeft(), node));
            if (root.getLeft() != null)
                root.getLeft().setParent(root);
        } else if (cmp > 0) {
            root.setRight(insert(root.getRight(), node));
            if (root.getRight() != null)
                root.getRight().setParent(root);
        } else {
            root.setValue(node.getValue());
            return root;
        }
        return balance(root);
    }

    @Override
    public void deleteNode(K key) {
        setRootNode(delete(getRootNode(), key));
    }

    private Node<K, V> delete(Node<K, V> root, K key) {
        if (root == null)
            return null;
        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeft(delete(root.getLeft(), key));
            if (root.getLeft() != null)
                root.getLeft().setParent(root);
        } else if (cmp > 0) {
            root.setRight(delete(root.getRight(), key));
            if (root.getRight() != null)
                root.getRight().setParent(root);
        } else {
            if (root.getLeft() == null || root.getRight() == null) {
                Node<K, V> temp = (root.getLeft() != null) ? root.getLeft() : root.getRight();
                if (temp != null)
                    temp.setParent(root.getParent());
                return temp;
            } else {
                Node<K, V> successor = minValueNode(root.getRight());
                root.setKey(successor.getKey());
                root.setValue(successor.getValue());
                root.setRight(delete(root.getRight(), successor.getKey()));
                if (root.getRight() != null)
                    root.getRight().setParent(root);
            }
        }
        return balance(root);
    }

    private int getHeight(Node<K, V> node) {
        if (node == null)
            return 0;
        if (node.hasLeft() || node.hasRight()) {
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }
        return 1;
    }

    private int getBalance(Node<K, V> node) {
        if (node == null)
            return 0;
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private Node<K, V> minValueNode(Node<K, V> node) {
        Node<K, V> current = node;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }
    
    private Node<K, V> rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null)
            y.getLeft().setParent(x);
        y.setLeft(x);
        y.setParent(x.getParent());
        x.setParent(y);
        return y;
    }

    private Node<K, V> rotateRight(Node<K, V> y) {
        Node<K, V> x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != null)
            x.getRight().setParent(y);
        x.setRight(y);
        x.setParent(y.getParent());
        y.setParent(x);
        return x;
    }

    private Node<K, V> balance(Node<K, V> node) {
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
                if (node.getLeft() != null)
                    node.getLeft().setParent(node);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (getBalance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
                if (node.getRight() != null)
                    node.getRight().setParent(node);
            }
            return rotateLeft(node);
        }
        return node;
    }
}