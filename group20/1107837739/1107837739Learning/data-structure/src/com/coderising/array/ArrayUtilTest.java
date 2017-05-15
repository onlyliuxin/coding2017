package com.coderising.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * ArrayUtil test
 *
 * Created by Korben on 26/02/2017.
 */
public class ArrayUtilTest {

    @Test
    public void reverseArray() throws Exception {
        // test reverse even number
        {
            int[] testArray = new int[5];
            for (int i = 0; i < 5; i++) {
                testArray[i] = i;
            }
            ArrayUtil.reverseArray(testArray);
            for (int i = 0; i < 5; i++) {
                Assert.assertEquals(5 - 1 - i, testArray[i]);
            }
        }

        // test reverse odd number
        {
            int[] testArray = new int[4];
            for (int i = 0; i < 4; i++) {
                testArray[i] = i;
            }
            ArrayUtil.reverseArray(testArray);
            for (int i = 0; i < 4; i++) {
                Assert.assertEquals(4 - 1 - i, testArray[i]);
            }
        }
    }

    @Test
    public void removeZero() throws Exception {
        // 测试非空数组
        {
            int[] testArray = new int[20];
            for (int i = 0; i < 20; i++) {
                if (i % 5 == 0) {
                    testArray[i] = 0;
                } else {
                    testArray[i] = i;
                }
            }

            int[] newArray = ArrayUtil.removeZero(testArray);
            Assert.assertNotNull(newArray);
            for (int i = 0; i < 20; i++) {
                if (i % 5 == 0) {
                    continue;
                }

                Assert.assertEquals(testArray[i], i);
            }
        }

        // 测试空数组
        {
            int[] testArray = new int[5];
            for (int i = 0; i < 5; i++) {
                testArray[i] = 0;
            }

            int[] newArray = ArrayUtil.removeZero(testArray);
            Assert.assertNotNull(newArray);
            Assert.assertEquals(newArray.length, 0);
        }
    }

    @Test
    public void merge() throws Exception {
        // 构建数组
        int[] array1 = new int[10];
        int[] array2 = new int[11];
        array2[10] = 100;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                array1[i / 2] = i; // 0, 2, 4, 6, 8
            } else {
                array2[i / 2] = i; // 1, 3, 5, 7, 9
            }
        }

        for (int i = 10; i < 15; i++) {
            array1[i - 5] = i; // 10, 11, 12, 13, 14, 15
            array2[i - 5] = i; // 10, 11, 12, 13, 14, 15
        }

        // 测试merge
        {
            int[] merge = ArrayUtil.merge(array1, array2);
            Assert.assertNotNull(merge);
            Assert.assertEquals(merge.length, 16);
            for (int i = 0; i < 15; i++) {
                Assert.assertEquals(merge[i], i);
            }
            Assert.assertEquals(merge[15], 100);
        }
        // 调换数组顺序
        {
            int[] merge = ArrayUtil.merge(array2, array1);
            Assert.assertNotNull(merge);
            Assert.assertEquals(merge.length, 16);
            for (int i = 0; i < 15; i++) {
                Assert.assertEquals(merge[i], i);
            }
            Assert.assertEquals(merge[15], 100);
        }
        // 测试空数组
        {
            int[] array3 = new int[0];
            int[] merge1 = ArrayUtil.merge(array1, array3);
            Assert.assertArrayEquals(merge1, array1);

            int[] merge2 = ArrayUtil.merge(array3, array1);
            Assert.assertArrayEquals(merge2, array1);
        }
        // 测试相同数组
        {
            int[] merge = ArrayUtil.merge(array1, array1);
            Assert.assertArrayEquals(merge, array1);
        }
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = new int[5];
        for (int i = 0; i < 5; i++) {
            oldArray[i] = i;
        }

        int[] newArray = ArrayUtil.grow(oldArray, 5);
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                Assert.assertEquals(newArray[i], i);
            } else {
                Assert.assertEquals(newArray[i], 0);
            }
        }
    }

    @Test
    public void fibonacci() throws Exception {
        int[] fibonacciArray = {1, 1, 2, 3, 5, 8, 13, 21};

        int[] calculatedFibonacci = ArrayUtil.fibonacci(22);
        Assert.assertArrayEquals(fibonacciArray, calculatedFibonacci);
    }

    @Test
    public void getPrimes() throws Exception {
        int[] expected = {2, 3, 5, 7, 11, 13, 17, 19};
        int[] primes = ArrayUtil.getPrimes(23);
        Assert.assertArrayEquals(primes, expected);
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int[] perfectNumbers = {6, 28, 496, 8128};
        int[] calculatedPerfectNumbers = ArrayUtil.getPerfectNumbers(8220);
        Assert.assertArrayEquals(perfectNumbers, calculatedPerfectNumbers);
    }

    @Test
    public void join() throws Exception {
        {
            int[] array = {1};
            String joinStr = ArrayUtil.join(array, "-");
            Assert.assertEquals("1", joinStr);
        }

        {
            int[] array = {1, 2, 3};
            String joinStr = ArrayUtil.join(array, "-");
            Assert.assertEquals("1-2-3", joinStr);
        }
    }
}