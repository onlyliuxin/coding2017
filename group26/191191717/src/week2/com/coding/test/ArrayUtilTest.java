package week2.com.coding.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week2.com.coding.basic.ArrayUtil;

public class ArrayUtilTest
{
    private static ArrayUtil arrayUtil = null;
    
    @Before
    public void init()
    {
        arrayUtil = new ArrayUtil();
    }
    
    @Test
    public void testReverseArray()
    {
        int[] arrays = {7, 9, 30, 3};
        arrayUtil.reverseArray(arrays);
    }
    
    @Test
    public void testRemoveZero()
    {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int newArr[] = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
        Assert.assertArrayEquals(newArr, arrayUtil.removeZero(oldArr));
    }
    
    @Test
    public void testMerge()
    {
        int[] array1 = {3, 5, 7, 8};
        int[] array2 = {4, 5, 6, 7};
        int[] vaildateArr = {3, 4, 5, 6, 7, 8};
        int[] arrs = arrayUtil.merge(array1, array2);
        Assert.assertArrayEquals(vaildateArr, arrs);
    }
    
    @Test
    public void testGrow()
    {
        int[] vaildateArr = {2, 3, 6, 0, 0, 0};
        int[] oldArr = {2, 3, 6};
        int[] arrs = arrayUtil.grow(oldArr, 3);
        Assert.assertArrayEquals(vaildateArr, arrs);
        
    }
    
    @Test
    public void testFibonacci()
    {
        int [] validateArr={1,1,2,3,5,8,13};
        int [] newArr=arrayUtil.fibonacci(15);
        Assert.assertArrayEquals(validateArr, newArr);
    }
    
    @Test
    public void testGetPrimes()
    {
        int[] arrs = arrayUtil.getPrimes(23);
        for (int i : arrs)
            System.out.printf("%d ", i);
    }
    
    @Test
    public void testGetPerfectNumbers()
    {
        int [] validateArr={6,28};
        int [] newArr=arrayUtil.getPerfectNumbers(100);
        Assert.assertArrayEquals(validateArr, newArr);
    }
    
    @Test
    public void testJoin()
    {
        String validateStr = "3-8-9";
        int[] oldArr = {3, 8, 9};
        String str = arrayUtil.join(oldArr, "-");
        Assert.assertEquals(validateStr, str);
    }
    
}
