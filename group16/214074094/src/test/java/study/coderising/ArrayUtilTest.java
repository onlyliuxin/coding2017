package study.coderising;

import org.junit.Assert;
import org.junit.Test;
import study.AbstractTest;
import study.coderising.array.ArrayUtil;

/**
 * @Author shane
 * @Time 2017/3/1 20:29
 * @Email shanbaohua@lxfintech.com
 * @Desc ...
 */
public class ArrayUtilTest extends AbstractTest {

    @Test
    public void testReverseArray(){
        int[] a = {7, 9 , 30, 3};
        printJson(a);
        ArrayUtil.reverseArray(a);
        printJson(a);
    }

    @Test
    public void testremoveZero(){
        int oldArr[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int newArr[] = ArrayUtil.removeZero(oldArr);
        printJson(oldArr);
        printJson(newArr);
    }

    @Test
    public void testMerge(){
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};

        int[] a3 = ArrayUtil.merge(a1, a2);
        printJson(a1);
        printJson(a2);
        printJson(a3);
    }

    @Test
    public void testGrow(){
        int[] oldArray = {2,3,6};
        printJson(oldArray);
        int[] newArray = ArrayUtil.grow(oldArray, 3);
        printJson(newArray);
    }

    @Test
    public void testFibonacci(){
        int[] onlyOne = {};
        int[] before15 = {1, 1, 2, 3, 5, 8, 13};
        int[] before21 = {1, 1, 2, 3, 5, 8, 13};
        Assert.assertArrayEquals(onlyOne, ArrayUtil.fibonacci(1));
        Assert.assertArrayEquals(before15, ArrayUtil.fibonacci(15));
        Assert.assertArrayEquals(before21, ArrayUtil.fibonacci(21));
    }

    @Test
    public void testGetPrimes(){
        int[] _19 = {2,3,5,7,11,13,17,19};
        Assert.assertArrayEquals(_19, ArrayUtil.getPrimes(23));
        int[] _32 = {2,3,5,7,11,13,17,19,23,29,31};
        Assert.assertArrayEquals(_32, ArrayUtil.getPrimes(32));
    }

    @Test
    public void testGetPerfectNumbers(){
        int[] arr = {6,28,496,8128,33550336};
        Assert.assertArrayEquals(arr, ArrayUtil.getPerfectNumbers(33550337));
    }

    @Test
    public void testJoin(){
        int[] arr = {3,8,9};
        String seperator = "-";
        Assert.assertEquals("3-8-9", ArrayUtil.join(arr, seperator));
    }

}
