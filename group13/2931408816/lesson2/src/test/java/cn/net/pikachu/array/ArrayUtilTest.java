package cn.net.pikachu.array; 

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/** 
* ArrayUtil Tester. 
* 
* @author pikachu 
* @since <pre>三月 1, 2017</pre> 
* @version 1.0 
*/ 
public class ArrayUtilTest { 

    public ArrayUtil util;
    @Before
    public void before() throws Exception { 
        util= new ArrayUtil();
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
    public void testReverseArray() { 
        int a[]={7,9,30,3};
        int ae[]={3,30,9,7};
        util.reverseArray(a);
        Assert.assertArrayEquals(ae,a);

        int b[]={7,9,30,3,4};
        int be[]={4,3,30,9,7};
        util.reverseArray(b);
        Assert.assertArrayEquals(be,b);
    } 

    /** 
    * 
    * Method: removeZero(int[] oldArray) 
    * 
    */ 
    @Test
    public void testRemoveZero() {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int arr[]={1,3,4,5,6,6,5,4,7,6,7,5};
        int r[] = util.removeZero(oldArr);
        Assert.assertEquals(Arrays.toString(r),Arrays.toString(arr));
    } 

    /** 
    * 
    * Method: merge(int[] array1, int[] array2) 
    * 
    */ 
    @Test
    public void testMerge() { 
        int[] a = {3, 5, 7,8};
        int[] b = {4, 5, 6,7};
        int[] c = {3,4,5,6,7,8};
        int[] r=util.merge(a,b);
        Assert.assertEquals(Arrays.toString(c),Arrays.toString(r));
    } 

    /** 
    * 
    * Method: grow(int [] oldArray, int size) 
    * 
    */ 
    @Test
    public void testGrow() { 
        int[] oldArray = {2,3,6};
        int[] a = {2,3,6,0,0,0};
        Assert.assertArrayEquals(a,util.grow(oldArray,3));
    }

    /** 
    * 
    * Method: fibonacci(int max) 
    * 
    */ 
    @Test
    public void testFibonacci() { 
        int[] a ={1,1,2,3,5,8,13};
        Assert.assertArrayEquals(a,util.fibonacci(15));
        Assert.assertArrayEquals(new int[]{},util.fibonacci(1));
    } 

    /** 
    * 
    * Method: getPrimes(int max) 
    * 
    */ 
    @Test
    public void testGetPrimes() { 
        int[] a = new int[]{2,3,5,7,11,13,17,19};
        Assert.assertArrayEquals(a,util.getPrimes(23));
    } 

    /** 
    * 
    * Method: getPerfectNumbers(int max) 
    * 
    */ 
    @Test
    public void testGetPerfectNumbers() { 
        
    } 

    /** 
    * 
    * Method: join(int[] array, String seperator) 
    * 
    */ 
    @Test
    public void testJoin() { 
        int[] a =new int[]{3,8,9};
        Assert.assertEquals("3-8-9",util.join(a,"-"));
    } 


    /** 
    * 
    * Method: getIntsFromList(List<Integer> list) 
    * 
    */ 
    @Test
    public void testGetIntsFromList() { 
        /* 
        try { 
           Method method = ArrayUtil.getClass().getMethod("getIntsFromList", List<Integer>.class); 
           method.setAccessible(true); 
           method.invoke(<Object>, <Parameters>); 
        } catch(NoSuchMethodException e) { 
        } catch(IllegalAccessException e) { 
        } catch(InvocationTargetException e) { 
        } 
        */ 
        } 

    /** 
    * 
    * Method: isPrimes(int num) 
    * 
    */ 
    @Test
    public void testIsPrimes() { 
        /* 
        try { 
           Method method = ArrayUtil.getClass().getMethod("isPrimes", int.class); 
           method.setAccessible(true); 
           method.invoke(<Object>, <Parameters>); 
        } catch(NoSuchMethodException e) { 
        } catch(IllegalAccessException e) { 
        } catch(InvocationTargetException e) { 
        } 
        */ 
        } 

    /** 
    * 
    * Method: isPerfectNumber(int num) 
    * 
    */ 
    @Test
    public void testIsPerfectNumber() { 
        /* 
        try { 
           Method method = ArrayUtil.getClass().getMethod("isPerfectNumber", int.class); 
           method.setAccessible(true); 
           method.invoke(<Object>, <Parameters>); 
        } catch(NoSuchMethodException e) { 
        } catch(IllegalAccessException e) { 
        } catch(InvocationTargetException e) { 
        } 
        */ 
        } 

} 
