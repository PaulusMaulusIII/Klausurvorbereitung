package main.java.search;

import java.util.NoSuchElementException;

public interface BinarySearch<K extends Comparable<K>> extends Search<K> {
    @Override
    default int indexOf(K key, K[] array) {
        int leftBound = 0;
        int rightBound = array.length - 1;
        while (leftBound <= rightBound) {
            int middle = leftBound + (int) Math.floor((rightBound - leftBound) / 2);
            if(array[middle].compareTo(key) < 0)
                leftBound = middle + 1;
            else if (array[middle].compareTo(key) > 0)
                rightBound = middle - 1;
            else
                return middle;
        }
        throw new NoSuchElementException("No such element in this set!");
    }
}
