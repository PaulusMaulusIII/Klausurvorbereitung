package main.java.sorting;

public interface InsertionSort<K extends Comparable<K>> extends Sort<K> {
    @Override
    default void sort(K[] array) {
        for (int i = 1; i < array.length; i++) {
            K sortVal = array[i];
            int j = i;
            while ((j > 0) && (array[j - 1].compareTo(sortVal) > 0)) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = sortVal;
        }
    }
}
