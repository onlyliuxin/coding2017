package coderising.array;


import com.coding.basic.datastructure.array.ArrayUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zt on 2017/2/27.
 */
public class ArrayUtilTest {

    private ArrayUtil arrayUtil = null;

    @Before
    public void setUp() {
        arrayUtil = new ArrayUtil();
    }

    @Test
    public void testReverseArray() {
        int[] a = {7, 9, 30, 3};
        int[] expectedReversedA = {3, 30, 9, 7};
        Assert.assertArrayEquals(expectedReversedA, arrayUtil.reverseArray(a));
        int[] b = {7, 9, 30, 3, 4};
        int[] expectedReversedB = {4, 3, 30, 9, 7};
        Assert.assertArrayEquals(expectedReversedB, arrayUtil.reverseArray(b));
    }

    @Test
    public void testRemoveZero() {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] expected = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
        Assert.assertArrayEquals(expected, arrayUtil.removeZero(oldArr));
    }

    @Test
    public void testFibonacci() {
        int max = 1;
        int[] expected = {0};
        Assert.assertArrayEquals(expected, arrayUtil.fibonacci(max));
        int max2 = 15;
        int[] expected2 = {0, 1, 1, 2, 3, 5, 8, 13};
        Assert.assertArrayEquals(expected2, arrayUtil.fibonacci(max2));
    }

    @Test
    public void testGetPrimes() {
        int[] expected = {2, 3, 5, 7, 11, 13, 17, 19};
        int max = 23;
        Assert.assertArrayEquals(expected, arrayUtil.getPrimes(max));
    }

    @Test
    public void testMerge() {
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        int[] expected = {3, 4, 5, 6, 7, 8};
        Assert.assertArrayEquals(expected, arrayUtil.merge(a1, a2));
    }

    @Test
    public void testGetPerfectNumbers() {
        int max = 1000;
        arrayUtil.getPerfectNumbers(max);
    }

    @Test
    public void testGrow() {
        int[] oldArray = {2, 3, 6};
        int size = 3;
        int[] newArray = arrayUtil.grow(oldArray, size);
        int[] expected = {2, 3, 6, 0, 0, 0};
        Assert.assertArrayEquals(expected, newArray);
    }

    @Test
    public void testJoin() {
        int[] array = {3, 8, 9};
        String seperator = "-";
        String joinedString = arrayUtil.join(array, seperator);
        Assert.assertEquals("3-8-9", joinedString);
    }

}
