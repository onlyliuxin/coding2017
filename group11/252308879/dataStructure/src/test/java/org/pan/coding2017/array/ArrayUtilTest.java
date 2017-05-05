package org.pan.coding2017.array;

import org.junit.Test;

import java.util.Arrays;

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
        int[] a1 = {3, 5, 7,8},  a2 = {4, 5, 6,7};
        //则 a3 为[3,4,5,6,7,8]
        int[] merge = ArrayUtil.merge(a1, a2);
        System.out.println(Arrays.toString(merge));
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
    //max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
        int[] fibonacci = ArrayUtil.fibonacci(988);
        System.out.println(Arrays.toString(fibonacci));
    }

    @Test
    public void getPrimes() throws Exception {
        //例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
        int[] primes = ArrayUtil.getPrimes(23);
        System.out.println(Arrays.toString(primes));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int[] primes = ArrayUtil.getPerfectNumbers(10000);
        System.out.println(Arrays.toString(primes));
    }

    @Test
    public void join() throws Exception {
        int [] array= {3,8,9};
        String seperator = "-";
        String result = ArrayUtil.join(array, seperator);
        System.out.println(result);
    }




}