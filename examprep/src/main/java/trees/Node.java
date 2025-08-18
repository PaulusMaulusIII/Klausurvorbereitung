package main.java.trees;

public class Node<K,V> {
    private K key;
    private V value;
    private Node<K,V> parent;
    private Node<K,V> left;
    private Node<K,V> right;
    private boolean hasLeft = false;
    private boolean hasRight = false;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(Node<K,V> parent, K key, V value) {
        this(key, value);
        setParent(parent);
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
        hasLeft = true;
    }

    public Node<K,V> getLeft() {
        return left;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
        hasRight = true;
    }

    public Node<K,V> getRight() {
        return this.right;
    }

    public boolean hasLeft() {
        return hasLeft;
    }

    public boolean hasRight() {
        return hasRight;
    }

    public Node<K, V> getParent() {
        return parent;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Key: "+key.toString()+" Value: "+value.toString();
    }
}
