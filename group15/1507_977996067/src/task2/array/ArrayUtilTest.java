package task2.array;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayUtilTest {

    private ArrayUtil util;

    @Before
    public void pre() {
        util = new ArrayUtil();
    }

    @Test
    public void reverseArray() throws Exception {
        int[] i = {7, 9, 40, 30, 3};
        int[] ii = {7, 9, 40, 30, 3, 11};
        util.reverseArray(i);
        util.reverseArray(ii);
        assertArrayEquals(new int[]{3, 30, 40, 9, 7}, i);
        assertArrayEquals(new int[]{11, 3, 30, 40, 9, 7}, ii);
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        assertArrayEquals(new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5}, util.removeZero(oldArr));
    }

    @Test
    public void merge() throws Exception {
        int[] arr1 = new int[]{3, 5, 7, 8};
        int[] arr2 = new int[]{4, 5, 6, 7};
        assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8}, util.merge(arr1, arr2));
    }

    @Test
    public void grow() throws Exception {
        int[] old = new int[]{2, 3, 6};
        assertArrayEquals(util.grow(old, 3), new int[]{2, 3, 6, 0, 0, 0});
    }

    @Test
    public void fibonacci() throws Exception {
        assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, util.fibonacci(15));
    }

    @Test
    public void getPrimes() throws Exception {
        int[] primes = util.getPrimes(33);
        assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31}, primes);
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        assertArrayEquals(new int[]{6,28,496,8128}, util.getPerfectNumbers(10000));
    }

    @Test
    public void join() throws Exception {
        int[] arr = new int[]{3, 8, 9};
        assertEquals("3-8-9", util.join(arr, "-"));
    }

    private void printArray(int[] arr) {
        Arrays.stream(arr).forEach(System.out::println);
    }


}