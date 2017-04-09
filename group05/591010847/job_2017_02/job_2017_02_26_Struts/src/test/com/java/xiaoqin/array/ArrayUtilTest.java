package test.com.java.xiaoqin.array;

import com.java.xiaoqin.array.ArrayUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * ArrayUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 4, 2017</pre>
 */
public class ArrayUtilTest {

    private ArrayUtil mArrayUtil;

    @Before
    public void before() throws Exception {
        mArrayUtil = new ArrayUtil();
    }

    @After
    public void after() throws Exception {
        mArrayUtil = null;
    }

    /**
     * Method: reverseArray(int[] origin)
     */
    @Test
    public void testReverseArray() throws Exception {
        int[] origin = {7, 9, 30, 3};
        mArrayUtil.reverseArray(origin);
        System.out.println(Arrays.toString(origin));
    }

    /**
     * Method: removeZero(int[] oldArray)
     */
    @Test
    public void testRemoveZero() throws Exception {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        System.out.println(Arrays.toString(mArrayUtil.removeZero(oldArr)));
    }

    /**
     * Method: merge(int[] array1, int[] array2)
     */
    @Test
    public void testMerge() throws Exception {
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        int[] mergeArr = mArrayUtil.merge(a1, a2);
        System.out.println(Arrays.toString(mergeArr));
    }

    /**
     * Method: grow(int[] oldArray, int size)
     */
    @Test
    public void testGrow() throws Exception {
        int[] oldArray = {2, 3, 6};
        int[] growArray = mArrayUtil.grow(oldArray, 3);
        System.out.println(Arrays.toString(growArray));
    }

    /**
     * Method: fibonacci(int max)
     */
    @Test
    public void testFibonacci() throws Exception {
        int[] fibonacciArray = mArrayUtil.fibonacci(1);
        System.out.println(Arrays.toString(fibonacciArray));
        fibonacciArray = mArrayUtil.fibonacci(15);
        System.out.println(Arrays.toString(fibonacciArray));
    }

    /**
     * Method: getPrimes(int max)
     */
    @Test
    public void testGetPrimes() throws Exception {
        int[] primes = mArrayUtil.getPrimes(23);
        System.out.println(Arrays.toString(primes));
    }

    /**
     * Method: getPerfectNumbers(int max)
     */
    @Test
    public void testGetPerfectNumbers() throws Exception {
        int[] perfectNumbers = mArrayUtil.getPerfectNumbers(10000);
        System.out.println(Arrays.toString(perfectNumbers));
    }

    /**
     * Method: join(int[] array, String seperator)
     */
    @Test
    public void testJoin() throws Exception {
        String join = mArrayUtil.join(new int[]{3, 8, 9}, "-");
        System.out.println(join);
    }


} 
