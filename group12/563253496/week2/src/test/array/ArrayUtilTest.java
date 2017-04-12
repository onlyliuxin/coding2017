package test.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import array.ArrayUtil;

import java.lang.reflect.Array;

/**
 * ArrayUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ÈýÔÂ 2, 2017</pre>
 */
public class ArrayUtilTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: reverseArray(int[] origin)
     */
    @Test
    public void testReverseArray() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();
        int[] a = {7, 9, 30, 3};
        int[] b = {3, 30, 9, 7};
        au.reverseArray(a);
        for (int i = 0; i < a.length; i++) {
            Assert.assertEquals(b[i], a[i]);
        }

        a = new int[]{7, 9, 30, 3, 4};
        b = new int[]{4, 3, 30, 9, 7};
        au.reverseArray(a);
        for (int i = 0; i < a.length; i++) {
            Assert.assertEquals(b[i], a[i]);
        }

    }

    /**
     * Method: removeZero(int[] oldArray)
     */
    @Test
    public void testRemoveZero() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();

        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int result[] = au.removeZero(oldArr);
        int exResult[] = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
        Assert.assertEquals(result.length,exResult.length);
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(result[i], exResult[i]);

        }
    }

    /**
     * Method: merge(int[] array1, int[] array2)
     */
    @Test
    public void testMerge() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        int[] a3 = {3, 4, 5, 6, 7, 8};
        int[] result = au.merge(a1,a2);
        Assert.assertEquals(a3.length, result.length);
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(a3[i],result[i]);
        }
    }

    /**
     * Method: grow(int[] oldArray, int size)
     */
    @Test
    public void testGrow() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();

        int[] oldArray = {2,3,6};
        int size = 3;

        int[] newArray = au.grow(oldArray,size);
        int[] exArray = {2,3,6,0,0,0};
        for (int i = 0; i < newArray.length; i++) {
            Assert.assertEquals(exArray[i],newArray[i]);
        }

    }

    /**
     * Method: fibonacci(int max)
     */
    @Test
    public void testFibonacci() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();
        int[] exArray = {1,1,2,3,5,8,13};
        int[] newArray = au.fibonacci(14);
        for (int i = 0; i < newArray.length; i++) {
            Assert.assertEquals(exArray[i],newArray[i]);
        }
    }

    /**
     * Method: getPrimes(int max)
     */
    @Test
    public void testGetPrimes() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();
        int[] exArray = {2,3,5,7,11,13,17,19};
        int[] newArray = au.getPrimes(23);
        for (int i = 0; i < newArray.length; i++) {
            Assert.assertEquals(exArray[i],newArray[i]);
        }
    }

    /**
     * Method: getPerfectNumbers(int max)
     */
    @Test
    public void testGetPerfectNumbers() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();
        int exArray[] = {6,28,496};
        int[] newArray = au.getPerfectNumbers(1000);
        for (int i = 0; i < newArray.length; i++) {
            Assert.assertEquals(exArray[i],newArray[i]);
        }


    }

    /**
     * Method: join(int[] array, String seperator)
     */
    @Test
    public void testJoin() throws Exception {
//TODO: Test goes here...
        ArrayUtil au = new ArrayUtil();
        String exS = "3-8-9";
        int[] array= {3,8,9};
        String result = au.join(array,"-");
        Assert.assertEquals(exS,result);
    }


} 
