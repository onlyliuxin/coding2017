import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
* ArrayUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 1, 2017</pre> 
* @version 1.0 
*/ 
public class ArrayUtilTest {
    int[] a={0,1,2,3,4,5,6,7,8,9};
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

   ArrayUtil.reverseArray(a);
    System.out.println(Arrays.toString(a));

} 

/** 
* 
* Method: removeZero(int[] oldArray) 
* 
*/ 
@Test
public void testRemoveZero() throws Exception {
    int[] a={0,1,0,3,4,0,0,7,8,0,10};
    System.out.println(Arrays.toString(ArrayUtil.removeZero(a)));
} 

/** 
* 
* Method: merge(int[] array1, int[] array2) 
* 
*/ 
@Test
public void testMerge() throws Exception {
    int[] a={0,1,0,3,4,-90,0,0,7,8,0,10};
    int[] b={-90,0,10};
    System.out.println(Arrays.toString(ArrayUtil.merge(a,b)));

//TODO: Test goes here... 
} 

/** 
* 
* Method: grow(int [] oldArray, int size) 
* 
*/ 
@Test
public void testGrow() throws Exception {
    int[] b= ArrayUtil.grow(a,4);
    System.out.println(Arrays.toString(b));
} 

/** 
* 
* Method: fibonacci(int max) 
* 
*/ 
@Test
public void testFibonacci() throws Exception {
    System.out.println(Arrays.toString(ArrayUtil.fibonacci(2)));
} 

/** 
* 
* Method: getPrimes(int max) 
* 
*/ 
@Test
public void testGetPrimes() throws Exception {
    System.out.println(Arrays.toString(ArrayUtil.getPrimes(99)));
} 

/** 
* 
* Method: getPerfectNumbers(int max) 
* 
*/ 
@Test
public void testGetPerfectNumbers() throws Exception {
    System.out.println(Arrays.toString(ArrayUtil.getPerfectNumbers(99)));
} 

/** 
* 
* Method: join(int[] array, String seperator) 
* 
*/ 
@Test
public void testJoin() throws Exception {
    System.out.println(ArrayUtil.join(a,"@"));
} 


} 
