package com.johnChnia.coderising2017.array;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by john on 2017/3/16.
 */
public class ArrayUtilTest {

    private ArrayUtil arrayUtil;
    private int[] array1;
    private int[] array2;
    private int[] array3;
    private int[] array4;
    private int[] array5;
    private int[] array6;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
        array1 = new int[]{7, 9, 30, 3};
        array2 = new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        array3 = new int[]{3, 5, 7, 8};
        array4 = new int[]{4, 5, 6, 7};
        array5 = new int[]{2, 3, 6};
        array6 = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
    }

    @Test
    public void testReverseArray() throws Exception {
        arrayUtil.reverseArray(array1);
        assertThat(array1[0], equalTo(3));
        assertThat(array1[1], equalTo(30));
        assertThat(array1[2], equalTo(9));
        assertThat(array1[3], equalTo(7));
    }

    @Test
    public void testRemoveZero() throws Exception {
        int[] newArray = arrayUtil.removeZero(array2);
        for (int element :
                newArray) {
            assertThat(element, not(0));
        }
    }

    @Test
    public void testMerge() throws Exception {
        int[] newArray = arrayUtil.merge(array3, array4);
        assertThat(newArray[0], equalTo(3));
        assertThat(newArray[1], equalTo(4));
        assertThat(newArray[2], equalTo(5));
        assertThat(newArray[3], equalTo(6));
        assertThat(newArray[4], equalTo(7));
        assertThat(newArray[5], equalTo(8));
    }

    @Test
    public void testGrow() throws Exception {
        int[] newArray = arrayUtil.grow(array5, 3);
        assertThat(newArray[0], equalTo(2));
        assertThat(newArray[1], equalTo(3));
        assertThat(newArray[2], equalTo(6));
        assertThat(newArray[3], equalTo(0));
        assertThat(newArray[4], equalTo(0));
        assertThat(newArray[5], equalTo(0));
    }

    @Test
    public void testFibonacci() throws Exception {
        int[] newArray = arrayUtil.fibonacci(15);
        assertThat(newArray[0], equalTo(1));
        assertThat(newArray[1], equalTo(1));
        assertThat(newArray[2], equalTo(2));
        assertThat(newArray[3], equalTo(3));
        assertThat(newArray[4], equalTo(5));
        assertThat(newArray[5], equalTo(8));
        assertThat(newArray[6], equalTo(13));
    }

    @Test
    public void testGetPrimes() throws Exception {
        int[] newArray = arrayUtil.getPrimes(23);
        assertThat(newArray[0], equalTo(2));
        assertThat(newArray[1], equalTo(3));
        assertThat(newArray[2], equalTo(5));
        assertThat(newArray[3], equalTo(7));
        assertThat(newArray[4], equalTo(11));
        assertThat(newArray[5], equalTo(13));
        assertThat(newArray[6], equalTo(17));
        assertThat(newArray[7], equalTo(19));
    }

    @Test
    public void testGetPerfectNumbers() throws Exception {
        int[] newArray = arrayUtil.getPerfectNumbers(100);
        assertThat(newArray.length, equalTo(2));
        assertThat(newArray[0], equalTo(6));
        assertThat(newArray[1], equalTo(28));
    }

    @Test
    public void testJoin() throws Exception {
        assertThat(arrayUtil.join(array6, "-"),
                containsString("2-3-5-7-11-13-17-19"));
    }

}