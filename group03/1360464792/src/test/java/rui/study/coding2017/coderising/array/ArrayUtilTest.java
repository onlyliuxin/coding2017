package rui.study.coding2017.coderising.array;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 创建于 2017-03-01.
 *
 * @author 赵睿
 */
public class ArrayUtilTest {
    ArrayUtil arrayUtil=new ArrayUtil();

    @Test
    public void reverseArray() throws Exception {
        int[] origin={7, 9 , 30, 3};
        origin=arrayUtil.reverseArray(origin);
        for (Integer i:origin) {
            System.out.println(i);
        }
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        oldArr=arrayUtil.removeZero(oldArr);
        for (Integer i:oldArr) {
            System.out.println(i);
        }
    }

    @Test
    public void merge() throws Exception {
        int arr1[]={3, 5, 7,8};
        int arr2[]={4, 5, 6,7};

        int oldArr[]=arrayUtil.merge(arr1,arr2);
        for (Integer i:oldArr) {
            System.out.println(i);
        }
    }

    @Test
    public void grow() throws Exception {
        int oldArr[]={2,3,6};
        oldArr=arrayUtil.grow(oldArr,3);
        for (Integer i:oldArr) {
            System.out.println(i);
        }
    }

    @Test
    public void fibonacci() throws Exception {
        for (Integer i:arrayUtil.fibonacci(0)) {
            System.out.println(i);
        }
        for (Integer i:arrayUtil.fibonacci(1)) {
            System.out.println(i);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        for (Integer i:arrayUtil.fibonacci(2)) {
            System.out.println(i);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        for (Integer i:arrayUtil.fibonacci(15)) {
            System.out.println(i);
        }
    }

    @Test
    public void getPrimes() throws Exception {
        for (Integer i:arrayUtil.getPrimes(100)) {
            System.out.println(i);
        }
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        for (Integer i:arrayUtil.getPerfectNumbers(1000)) {
            System.out.println(i);
        }
    }

    @Test
    public void join() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        System.out.println(arrayUtil.join(oldArr,"````"));
    }

}