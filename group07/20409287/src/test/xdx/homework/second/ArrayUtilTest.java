package xdx.homework.second;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.testng.Assert;
import xdx.homework.second.array.ArrayUtil;

import java.util.Arrays;

/**
 * ArrayUtil Tester.
 *
 * @author <Authors name>
 * @since <pre>���� 4, 2017</pre>
 * @version 1.0
 */
public class ArrayUtilTest {

    ArrayUtil arrayUtil = new ArrayUtil();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: reverseArray(int[] origin)
     *
     */
    @Test
    public void testReverseArray() throws Exception {

        int[] array = {1,2,3,4,5,6,7,8,9};
        System.out.println("===================数组反转开始===================");
        System.out.println("原数组: " + Arrays.toString(array));
        arrayUtil.reverseArray(array);
        System.out.println("反转后的数组: " + Arrays.toString(array));
        System.out.println("===================数组反转结束===================" + "\n");
    }

    /**
     *
     * Method: removeZero(int[] oldArray)
     *
     */
    @Test
    public void testRemoveZero() throws Exception {

        System.out.println("===================数组清零开始===================");
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        System.out.println("原数组: " + Arrays.toString(oldArr));
        System.out.println("清零后的数组: " + Arrays.toString(arrayUtil.removeZero(oldArr)));
        System.out.println("===================数组清零结束===================" + "\n");
    }

    /**
     *
     * Method: merge(int[] array1, int[] array2)
     *
     */
    @Test
    public void testMerge() throws Exception {

        System.out.println("===================数组合并开始===================");
        int[] array1 = {1,2,4,5};
        int[] array2 = {3,4,5,6,7,8,9};
        System.out.println("原数组1: " + Arrays.toString(array1));
        System.out.println("原数组2: " + Arrays.toString(array2));
        int[] mergedArray = arrayUtil.merge(array1, array2);
        System.out.println("合并后的数组: " + Arrays.toString(mergedArray));
        System.out.println("===================数组合并结束===================" + "\n");
    }

    /**
     *
     * Method: grow(int [] oldArray, int size)
     *
     */
    @Test
    public void testGrow() throws Exception {

        System.out.println("===================数组增长开始===================");
        int[] array2 = {3,4,5,6,7,8,9};
        final int GROW_SIZE = 5;
        int[] growArray = arrayUtil.grow(array2, GROW_SIZE);
        Assert.assertEquals(array2.length + GROW_SIZE, growArray.length);
        System.out.println("原数组: " + Arrays.toString(array2));
        System.out.println("增长" + GROW_SIZE + "个单位后的数组: " + Arrays.toString(growArray));
        System.out.println("===================数组增长结束===================" + "\n");
    }

    /**
     *
     * Method: fibonacci(int max)
     *
     */
    @Test
    public void testFibonacci() throws Exception {

        System.out.println("===================斐波那契数列开始===================");
        final int MAX = 10000000;
        System.out.println(MAX + "以内的斐波那契数列: " + Arrays.toString(arrayUtil.fibonacci(MAX)));
        System.out.println("===================斐波那契数列结束===================" + "\n");
    }

    /**
     *
     * Method: getPrimes(int max)
     *
     */
    @Test
    public void testGetPrimes() throws Exception {

        System.out.println("===================素数计算开始===================");
        final int MAX = 10000;
        System.out.println(MAX + "以内的素数: " + Arrays.toString(arrayUtil.getPrimes(MAX)));
        System.out.println("===================素数计算结束===================" + "\n");
    }

    /**
     *
     * Method: getPerfectNumbers(int max)
     *
     */
    @Test
    public void testGetPerfectNumbers() throws Exception {

        System.out.println("===================计算完美数列结束===================");
        final int MAX = 10000;
        System.out.println(MAX + "以内的完数分别是: " + Arrays.toString(arrayUtil.getPerfectNumbers(MAX)));
        System.out.println("===================计算完美数列结束===================" + "\n");
    }

    /**
     *
     * Method: join(int[] array, String seperator)
     *
     */
    @Test
    public void testJoin() throws Exception {

        System.out.println("===================数组分隔开始===================");
        int[] array2 = {3,4,5,6,7,8,9};
        final String SEP = "-";
        System.out.println("原数组: " + Arrays.toString(array2));
        System.out.println("分隔符: " + SEP);
        System.out.println("分隔后的数组: " + arrayUtil.join(array2, SEP));
        System.out.println("===================数组分隔结束===================" + "\n");
    }


}
