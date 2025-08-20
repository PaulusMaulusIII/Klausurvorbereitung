package test.java;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import main.java.sorting.CountingSort;
import main.java.tools.InputGenerator;

class CountingSortTest implements CountingSort<Integer> {

    private CountingSortTest s;

    @BeforeEach
    void setUp() throws Exception {
        s = new CountingSortTest();
    }

    @RepeatedTest(20)
    void testCorrectness() {
        int[] temp = InputGenerator.randomSequence(25);
        Integer[] a = java.util.Arrays.stream(temp).boxed().toArray(Integer[]::new);
        s.sort(a);
        assertTrue(isSorted(a));
    }

}
