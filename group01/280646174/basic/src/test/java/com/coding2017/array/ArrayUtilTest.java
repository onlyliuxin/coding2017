package com.coding2017.array;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by kaitao.li on 2017/3/4.
 */
public class ArrayUtilTest {

    private ArrayUtil arrayUtil = new ArrayUtil();

    @Test
    public void reverseArray() throws Exception {
        int[] oddArray = new int[] { 1, 2, 3 };
        arrayUtil.reverseArray(oddArray);
        assertArrayEquals(oddArray, new int[] { 3, 2, 1 });

        int[] evenArray = new int[] { 1, 2, 3, 4 };
        arrayUtil.reverseArray(evenArray);
        assertArrayEquals(evenArray, new int[] { 4, 3, 2, 1 });
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[] = new int[] { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
        int[] newArray = arrayUtil.removeZero(oldArr);
        assertArrayEquals(newArray, new int[] { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 });
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = new int[] { 3, 5, 7, 8 };
        int[] a2 = new int[] { 4, 5, 6, 7 };
        int[] merge = arrayUtil.merge(a1, a2);
        assertArrayEquals(merge, new int[] { 3, 4, 5, 6, 7, 8 });
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = new int[] { 2, 3, 6 };
        int[] grow = arrayUtil.grow(oldArray, 3);
        assertArrayEquals(grow, new int[] { 2, 3, 6, 0, 0, 0 });
    }

    @Test
    public void fibonacci() throws Exception {
        int[] fibonacci = arrayUtil.fibonacci(15);
        assertArrayEquals(fibonacci, new int[] { 1, 1, 2, 3, 5, 8, 13 });
    }

    @Test
    public void getPrimes() throws Exception {
        int[] primes = arrayUtil.getPrimes(23);
        assertArrayEquals(primes, new int[] { 2, 3, 5, 7, 11, 13, 17, 19 });
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int[] perfectNumbers = arrayUtil.getPerfectNumbers(1000);
        assertArrayEquals(perfectNumbers, new int[] { 6, 28, 496 });
    }

    @Test
    public void join() throws Exception {
        int[] array = new int[] { 1, 2, 3 };
        assertTrue("1-2-3".equals(arrayUtil.join(array, "-")));
    }

}