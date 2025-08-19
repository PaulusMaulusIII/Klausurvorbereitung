package main.java.sorting;

public interface Sort<K extends Comparable<K>> {
    public void sort(K[] array);

    default void swap(int index0, int index1, K[] array) {
        K temp = array[index1];
        array[index1] = array[index0];
        array[index0] = temp;
    }
}
