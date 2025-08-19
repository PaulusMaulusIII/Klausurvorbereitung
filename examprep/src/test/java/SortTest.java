package test.java;

public interface SortTest<K extends Comparable<K>> {

    default boolean isSorted(K[] f) {
        for (int i = 0; i < f.length - 1; i++) {
            if (f[i].compareTo(f[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

}