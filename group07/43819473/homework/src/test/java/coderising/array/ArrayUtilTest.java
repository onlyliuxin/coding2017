package coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by zj on 2017/3/4.
 */
public class ArrayUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void reverseArray() throws Exception {
        Assert.assertArrayEquals(new int[]{3, 30, 9, 7}, ArrayUtil.reverseArray(new int[]{7, 9, 30, 3}));
        Assert.assertArrayEquals(new int[]{6, 3, 30, 9, 7}, ArrayUtil.reverseArray(new int[]{7, 9, 30, 3, 6}));
    }

    @Test
    public void removeZero() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5},
                ArrayUtil.removeZero(new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5}));
    }

    @Test
    public void merge() throws Exception {
        Assert.assertArrayEquals(new int[]{}, ArrayUtil.merge(new int[]{}, new int[]{}));
        Assert.assertArrayEquals(new int[]{1, 2}, ArrayUtil.merge(new int[]{}, new int[]{1, 2}));
        Assert.assertArrayEquals(new int[]{1, 2}, ArrayUtil.merge(new int[]{1, 2}, new int[]{}));
        Assert.assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8}, ArrayUtil.merge(new int[]{3, 5, 7, 8}, new int[]{4, 5, 6, 7}));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 6, 7, 8, 9}, ArrayUtil.merge(new int[]{3, 5, 7, 8}, new int[]{2, 5, 6, 9}));
        Assert.assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8}, ArrayUtil.merge(new int[]{4, 5, 6, 7}, new int[]{3, 5, 7, 8}));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 6, 7, 8, 9}, ArrayUtil.merge(new int[]{2, 5, 6, 9}, new int[]{3, 5, 7, 8}));
    }

    @Test
    public void grow() throws Exception {
        Assert.assertArrayEquals(new int[]{2, 3, 6, 0, 0, 0}, ArrayUtil.grow(new int[]{2, 3, 6}, 3));
    }

    @Test
    public void fibonacci() throws Exception {
        Assert.assertArrayEquals(new int[]{}, ArrayUtil.fibonacci(1));
        Assert.assertArrayEquals(new int[]{1, 1}, ArrayUtil.fibonacci(2));
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, ArrayUtil.fibonacci(15));
    }

    @Test
    public void getPrimes() throws Exception {
        Assert.assertArrayEquals(new int[]{}, ArrayUtil.getPrimes(2));
        Assert.assertArrayEquals(new int[]{2}, ArrayUtil.getPrimes(3));
        Assert.assertArrayEquals(new int[]{2, 3}, ArrayUtil.getPrimes(4));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19}, ArrayUtil.getPrimes(23));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        Assert.assertArrayEquals(new int[]{6,28}, ArrayUtil.getPerfectNumbers(100));
//        System.out.println(Arrays.toString(ArrayUtil.getPerfectNumbers(40000000)));
    }

    @Test
    public void join() throws Exception {
        Assert.assertEquals("", ArrayUtil.join(new int[]{}, "-"));
        Assert.assertEquals("3", ArrayUtil.join(new int[]{3}, "-"));
        Assert.assertEquals("3-8-9", ArrayUtil.join(new int[]{3, 8, 9}, "-"));
    }
}