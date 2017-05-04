package datastructure.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

    private ArrayUtil arrayUtil;

    @Before
    public void setUp() {
        arrayUtil = new ArrayUtil();
    }

    @Test
    public void testReverseArray() {
        int[] a = {};
        arrayUtil.reverseArray(a);
        Assert.assertArrayEquals(new int[] {}, a);

        int[] b = {1, 2, 3};
        arrayUtil.reverseArray(b);
        Assert.assertArrayEquals(new int[] {3, 2, 1}, b);

        int[] c = {1, 2, 3, 4};
        arrayUtil.reverseArray(c);
        Assert.assertArrayEquals(new int[] {4, 3, 2, 1}, c);

        int[] d = new int[5000];
        int[] d1 = new int[5000];
        for (int i = 0; i < 5000; i++) {
            d[i] = i;
            d1[i] = 4999 - i;
        }
        arrayUtil.reverseArray(d);
        Assert.assertArrayEquals(d1, d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveZero() {
        int oldArr1[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int newArr1[] = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
        Assert.assertArrayEquals(newArr1, arrayUtil.removeZero(oldArr1));

        int oldArr2[] = {0, 0, 0, 0, 0};
        int newArr2[] = {};
        Assert.assertArrayEquals(newArr2, arrayUtil.removeZero(oldArr2));

        int oldArr3[] = {0, 0, 0, 0, 0, 1, 2, 3};
        int newArr3[] = {1, 2, 3};
        Assert.assertArrayEquals(newArr3, arrayUtil.removeZero(oldArr3));

        int oldArr4[] = {1, 2, 3, 0, 0, 0, 0, 0};
        int newArr4[] = {1, 2, 3};
        Assert.assertArrayEquals(newArr4, arrayUtil.removeZero(oldArr4));

        int oldArr5[] = {0, 0, 0, 0, 0, 1, 2, 3, 4, 0, 0, 0, 0};
        int newArr5[] = {1, 2, 3, 4};
        Assert.assertArrayEquals(newArr5, arrayUtil.removeZero(oldArr5));

        int oldArr6[] = {};
        int newArr6[] = {};
        Assert.assertArrayEquals(newArr6, oldArr6);

        arrayUtil.removeZero(null);
    }

    @Test
    public void testMerge() {
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        Assert.assertArrayEquals(new int[] {3, 4, 5, 6, 7, 8}, arrayUtil.merge(a1, a2));

        int[] b1 = {1, 2, 9, 100};
        int[] b2 = {3, 4, 5, 90, 100};
        Assert.assertArrayEquals(new int[] {1, 2, 3, 4, 5, 9, 90, 100}, arrayUtil.merge(b1, b2));

        int[] c1 = {};
        int[] c2 = {1, 2, 3};
        Assert.assertArrayEquals(new int[] {1, 2, 3}, arrayUtil.merge(c1, c2));

        int[] d1 = {};
        int[] d2 = {};
        Assert.assertArrayEquals(new int[] {}, arrayUtil.merge(d1, d2));
    }

    @Test
    public void testGrow() {
        int[] a1 = {2, 3, 6};
        Assert.assertArrayEquals(new int[] {2, 3, 6, 0, 0, 0}, arrayUtil.grow(a1, 3));
        Assert.assertArrayEquals(new int[] {2, 3, 6}, arrayUtil.grow(a1, 0));
    }

    @Test
    public void testFibonacci() {
        Assert.assertArrayEquals(new int[] {}, arrayUtil.fibonacci(1));
        Assert.assertArrayEquals(new int[] {1, 1, 2, 3, 5, 8, 13}, arrayUtil.fibonacci(15));

        int[] a = new int[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
                2584, 4181, 6765};
        Assert.assertArrayEquals(a, arrayUtil.fibonacci(6766));
    }

    @Test
    public void testGetPrimes() {
        Assert.assertArrayEquals(new int[] {}, arrayUtil.getPrimes(1));
        Assert.assertArrayEquals(new int[] {}, arrayUtil.getPrimes(2));
        Assert.assertArrayEquals(new int[] {2, 3, 5, 7, 11, 13, 17, 19}, arrayUtil.getPrimes(23));
        Assert.assertArrayEquals(new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139,
                149, 151, 157, 163, 167, 173, 179}, arrayUtil.getPrimes(181));
    }

    @Test
    public void testGetPerfectNumbers() {
        Assert.assertArrayEquals(new int[] {6}, arrayUtil.getPerfectNumbers(7));
        Assert.assertArrayEquals(new int[] {6, 28}, arrayUtil.getPerfectNumbers(496));
        Assert.assertArrayEquals(new int[] {6, 28, 496}, arrayUtil.getPerfectNumbers(497));
        Assert.assertArrayEquals(new int[] {6, 28, 496, 8128}, arrayUtil.getPerfectNumbers(8129));

    }

    @Test
    public void testJoin() {
        Assert.assertEquals("", arrayUtil.join(new int[] {}, "-"));
        Assert.assertEquals("1", arrayUtil.join(new int[] {1}, "-"));
        Assert.assertEquals("1-2-8-3-4-5", arrayUtil.join(new int[] {1, 2, 8, 3, 4, 5}, "-"));
        Assert.assertEquals("1*-*2*-*8*-*3*-*4*-*5",
                arrayUtil.join(new int[] {1, 2, 8, 3, 4, 5}, "*-*"));
    }

}
