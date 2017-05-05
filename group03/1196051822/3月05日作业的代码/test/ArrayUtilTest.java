package com.byhieg.coding2017.homework305;

import com.byhieg.utils.bprint.FullPrint;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/3/1.
 * Mail to byhieg@gmail.com
 */
public class ArrayUtilTest extends TestCase {
    public void testReverseArray() throws Exception {
        int[] array = new int[]{1, 2, 4, 5};
        ArrayUtil util = new ArrayUtil();
        util.reverseArray(array);
        for (int i = 0 ; i < array.length;i++) {
            System.out.print(array[i]);
        }

    }

    public void testRemoveZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        ArrayUtil util = new ArrayUtil();
        int [] newArray = util.removeZero(oldArr);
        for (int i = 0 ; i < newArray.length;i++) {
            System.out.print(newArray[i] + " ");
        }
    }

    public void testMerge() throws Exception {
        int [] a1 = new int[]{3, 5,7,8,10,11};
        int [] a2 = new int[]{4, 5, 6,7};
        int[] newArray = new ArrayUtil().merge(a1,a2);
        for (int i = 0 ; i < newArray.length;i++) {
            System.out.print(newArray[i] + " ");
        }

    }

    public void testGrow() throws Exception {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int [] newArray = new ArrayUtil().grow(a,3);
        for (int i = 0 ; i < newArray.length;i++) {
            System.out.print(newArray[i] + " ");
        }

    }


    public void testFibonacci()throws Exception {
        int[] newArray = new ArrayUtil().fibonacci(2);
        for (int i = 0 ; i < newArray.length;i++) {
            System.out.print(newArray[i] + " ");
        }

    }

    public void testgetPrimes() throws Exception {
        int[] newArray = new ArrayUtil().getPrimes(23);
        for (int i = 0 ; i < newArray.length;i++) {
            System.out.print(newArray[i] + " ");
        }

    }

    public void testgetPerfectNumbers() throws Exception {
        int [] newArray = new ArrayUtil().getPerfectNumbers(100);
        for (int i = 0 ; i < newArray.length;i++) {
            System.out.print(newArray[i] + " ");
        }

    }

    public void testJoin() throws Exception {
        int[] a = new int[]{3, 8, 9};
        System.out.println(new ArrayUtil().join(a,"-"));

    }

}