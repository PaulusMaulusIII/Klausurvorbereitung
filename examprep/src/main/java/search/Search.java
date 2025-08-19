package main.java.search;

public interface Search<K extends Comparable<K>> {
    public int indexOf(K key, K[] array);
}
