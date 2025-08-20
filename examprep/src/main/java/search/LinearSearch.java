package main.java.search;

import java.util.NoSuchElementException;

public interface LinearSearch<K extends Comparable<K>> extends Search<K> {
    @Override
    default int indexOf(K key, K[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equals(key))
                return i;
        throw new NoSuchElementException();
    }
}
