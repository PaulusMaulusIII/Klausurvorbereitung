package main.java.sorting;

public interface Sort<K extends Comparable<K>> {
    public void sort(K[] array);

    default void swap(int index0, int index1, K[] array) {
        K temp = array[index1];
        array[index1] = array[index0];
        array[index0] = temp;
    }

    default boolean isSorted(K[] f) {
        for (int i = 0; i < f.length - 1; i++) {
            if (f[i].compareTo(f[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
