package code02;

import org.junit.Test;

/**
 * Created by yaoyuan on 2017/3/13.
 */
public class ArrayUtilTest {

    @Test
    public void testReverseArray() throws Exception {
        int[] arr1 = new int[]{1,2,3,4,5,6,7};
        ArrayUtil arrayUtil = new ArrayUtil();
        arrayUtil.reverseArray(arr1);
        arrayUtil.printArr(arr1);


    }

    @Test
    public void testRemoveZero() throws Exception {
        int[] arr1 = new int[]{1,0,2,3,0,0,4,5,6,0};
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] arr2 = arrayUtil.removeZero(arr1);
        arrayUtil.printArr(arr2);
    }

    @Test
    public void testMerge() throws Exception {
        int[] arr1 = new int[]{3, 5, 7, 8};
        int[] arr2 = new int[]{4, 5, 6, 7};
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] arr3 = arrayUtil.merge(arr1,arr2);
        arrayUtil.printArr(arr3);
    }

    @Test
    public void testGrow() throws Exception {
        int[] arr1 = new int[]{1,2,3,4,5,6,7};
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] arr2 = arrayUtil.grow(arr1,3);
        arrayUtil.printArr(arr2);
    }

    @Test
    public void testFibonacci() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] arr1 = arrayUtil.fibonacci(4);
        arrayUtil.printArr(arr1);

        int[] arr2 = arrayUtil.fibonacci(20);
        arrayUtil.printArr(arr2);
    }

    @Test
    public void testGetPrimes() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        arrayUtil.printArr(arrayUtil.getPrimes(30));
    }

    @Test
    public void testGetPerfectNumbers() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        arrayUtil.printArr(arrayUtil.getPerfectNumbers(1000));
    }

    @Test
    public void testJoin() throws Exception {
        int[] arr1 = new int[]{1,2,3,4,5,6,7};
        ArrayUtil arrayUtil = new ArrayUtil();
        System.out.println(arrayUtil.join(arr1,"-"));
    }
}