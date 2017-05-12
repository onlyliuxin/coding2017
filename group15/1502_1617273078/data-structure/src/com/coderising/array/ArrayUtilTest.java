package com.coderising.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* ArrayUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>¶þÔÂ 27, 2017</pre> 
* @version 1.0 
*/ 
public class ArrayUtilTest { 

@Before
public void before() throws Exception {
    ArrayUtil arrayUtil = new ArrayUtil();
    int[] a = new int[10];
    a[0] = 1;
    a[1] = 2;
    a[2] = 3;
    a[3] = 4;
    a[4] = 5;
    a[5] = 6;
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
//TODO: Test goes here...
    int[] a = new int[10];
    a[0] = 1;
    a[1] = 2;
    a[2] = 3;
    a[3] = 4;
    a[4] = 5;
    a[5] = 6;
    ArrayUtil arrayUtil = new ArrayUtil();
    for (int i = 0; i <a.length ; i++) {
        System.out.print(a[i]);
    }
    System.out.println();
    //System.out.println(a.);
    arrayUtil.reverseArray(a);
} 

/** 
* 
* Method: removeZero(int[] oldArray) 
* 
*/ 
@Test
public void testRemoveZero() throws Exception { 
//TODO: Test goes here...
    int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
    ArrayUtil arrayUtil = new ArrayUtil();
    int[] a = arrayUtil.removeZero(oldArr);
    for (int i = 0; i <a.length ; i++) {
        System.out.print(a[i]);
    }
} 

/** 
* 
* Method: merge(int[] array1, int[] array2) 
* 
*/ 
@Test
public void testMerge() throws Exception { 
//TODO: Test goes here...
    int[]a1={3,5,7,8};


    int[] a2 = {4, 5, 6, 7};
    ArrayUtil arrayUtil = new ArrayUtil();
    int[]c=arrayUtil.merge(a1, a2);
    for (int i = 0; i <c.length ; i++) {
        System.out.print(c[i]);
    }
} 

/** 
* 
* Method: grow(int [] oldArray, int size) 
* 
*/ 
@Test
public void testGrow() throws Exception { 
//TODO: Test goes here...
    int[] oldArray = {2, 3, 6};
    ArrayUtil arrayUtil = new ArrayUtil();
    int[] arry=arrayUtil.grow(oldArray, 3);
    for (int i = 0; i <arry.length ; i++) {
        System.out.print(arry[i]);

    }
} 

/** 
* 
* Method: fibonacci(int max) 
* 
*/ 
@Test
public void testFibonacci() throws Exception { 
//TODO: Test goes here...
    ArrayUtil arrayUtil = new ArrayUtil();
    int[] he=arrayUtil.fibonacci(13);
    for (int i = 0; i <he.length ; i++) {
        System.out.print(he[i]);
    }
} 

/** 
* 
* Method: getPrimes(int max) 
* 
*/ 
@Test
public void testGetPrimes() throws Exception { 
//TODO: Test goes here...
    ArrayUtil arrayUtil = new ArrayUtil();
    int[] he=arrayUtil.getPrimes(13);
    for (int i = 0; i <he.length ; i++) {
        System.out.print(he[i]);
    }
} 

/** 
* 
* Method: getPerfectNumbers(int max) 
* 
*/ 
@Test
public void testGetPerfectNumbers() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: join(int[] array, String seperator) 
* 
*/ 
@Test
public void testJoin() throws Exception { 
//TODO: Test goes here...
    ArrayUtil arrayUtil = new ArrayUtil();
    int[] array = {3, 8, 9,8,5};
    String seperator = "-";
    System.out.println(arrayUtil.join(array, seperator));
} 


} 
