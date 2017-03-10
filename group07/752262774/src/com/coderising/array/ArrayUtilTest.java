package com.coderising.array;

/**
 * Created by yrs on 2017/3/4.
 */
public class ArrayUtilTest {

    @org.junit.Test
    public void testReverseArray() throws Exception {
        int[] array = new int[5];
        for(int i=0; i<5; i++) {
            array[i] = i;
        }
        ArrayUtil util = new ArrayUtil();
        util.reverseArray(array);
        for(int i=0; i<5; i++) {
            assert (array[i] == 4-i);
        }
    }

    @org.junit.Test
    public void testRemoveZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        ArrayUtil util = new ArrayUtil();
        int[] newArr = util.removeZero(oldArr);
        for(int item: newArr) {
            System.out.println(item);
            assert (item != 0);
        }
    }

    @org.junit.Test
    public void testMerge() throws Exception {
        int[] a1 = {3,5,7,8,22};
        int[] a2 = {4,5,6,7,8,9,11};
        int[] a3 = {3,4,5,6,7,8,9,11,22};
        ArrayUtil util = new ArrayUtil();
        int[] merge = util.merge(a1, a2);
        for(int i=0;i<a3.length;i++) {
            assert (merge[i] == a3[i]);
        }

    }

    @org.junit.Test
    public void testGrow() throws Exception {
        int[] oldArray = {1,2,3};
        int[] newArray = {1,2,3,0,0,0,0};
        ArrayUtil util = new ArrayUtil();
        int[] grow = util.grow(oldArray, 4);
        for(int i=0; i<newArray.length; i++) {
            assert (newArray[i] == grow[i]);
        }
    }

    @org.junit.Test
    public void testFibonacci() throws Exception {
        int[] fib = {1,1,2,3,5,8,13,21};
        ArrayUtil util = new ArrayUtil();
        int[] test = util.fibonacci(25);
        for(int i=0; i<fib.length; i++) {
            assert (fib[i] == test[i]);
        }
    }

    @org.junit.Test
    public void testGetPrimes() throws Exception {
        int[] primes = {2,3,5,7,11,13,17,19,23,29};
        ArrayUtil util = new ArrayUtil();
        int[] test = util.getPrimes(30);
        for(int i=0; i<primes.length; i++) {
            assert (primes[i] == test[i]);
        }

    }

    @org.junit.Test
    public void testGetPerfectNumbers() throws Exception {
        int[] perfect = {6,28,496,8128};
        ArrayUtil util = new ArrayUtil();
        int[] test = util.getPerfectNumbers(10000);
        for(int i=0; i<perfect.length; i++) {
            assert (perfect[i] == test[i]);
        }
    }

    @org.junit.Test
    public void testJoin() throws Exception {
        String str = "2-3-4-5-6";
        int[] array = {2,3,4,5,6};
        ArrayUtil util = new ArrayUtil();
        String test = util.join(array, "-");
        assert (str.equals(test));
    }
}