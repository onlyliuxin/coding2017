package org.apn.coding2017.array;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by QiPan on 2017/2/27.
 */
public class ArrayUtilTest {

    @Test
    public void removeZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        System.out.println("removeZero 移除0之前： "+ Arrays.toString(oldArr));
        int[] newArrays = ArrayUtil.removeZero(oldArr);
        System.out.println("removeZero 移除0之后： "+ Arrays.toString(newArrays));
    }

    @Test
    public void removeZero_2() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        System.out.println("removeZero_2 移除0之前： "+ Arrays.toString(oldArr));
        int[] newArrays = ArrayUtil.removeZero_2(oldArr);
        System.out.println("removeZero_2 移除0之后： "+ Arrays.toString(newArrays));
    }

    @Test
    public void reverseArray() throws Exception {
        int[] array = new int[]{7, 9 , 30, 3};
        int[] array2 = new int[] {7, 9, 30, 3, 4};
        System.out.println("置换前: " + Arrays.toString(array));
        ArrayUtil.reverseArray(array);
        System.out.println("置换后: "+ Arrays.toString(array));
        System.out.println("置换前: " + Arrays.toString(array2));
        ArrayUtil.reverseArray(array2);
        System.out.println("置换后: "+ Arrays.toString(array2));
    }

    @Test
    public void merge() throws Exception {

    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = {2,3,6} ;
        int size = 3;
        System.out.println("grow 之前："+ Arrays.toString(oldArray));
        int[] newArrays = ArrayUtil.grow(oldArray, size);
        System.out.println("grow 之后："+ Arrays.toString(newArrays));

    }

    @Test
    public void fibonacci() throws Exception {

    }

    @Test
    public void getPrimes() throws Exception {

    }

    @Test
    public void getPerfectNumbers() throws Exception {

    }

    @Test
    public void join() throws Exception {

    }




}