/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CJ
 */
public class ArrayUtilTest {
    
    public ArrayUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of reverseArray method, of class ArrayUtil.
     */
    @Test
    public void testReverseArray() {
        System.out.println("reverseArray");
        int[] origin = new int[]{1,2,3,4,5};
        int[] expecteds  = new int[]{5,4,3,2,1};
        ArrayUtil instance = new ArrayUtil();
        instance.reverseArray(origin);
        Assert.assertArrayEquals(expecteds, origin);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeZero method, of class ArrayUtil.
     */
    @Test
    public void testRemoveZero() {
        System.out.println("removeZero");
        int[] oldArray = new int[]{1,2,3,4,5,6,0,0,0,0};
        ArrayUtil instance = new ArrayUtil();
        int[] expResult = new int[]{1,2,3,4,5,6};
        int[] result = instance.removeZero(oldArray);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class ArrayUtil.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        int[] array1 = new int[]{3, 5, 7,8};
        int[] array2 = new int[]{4, 5, 6,7};
        ArrayUtil instance = new ArrayUtil();
        int[] expResult = new int[]{3,4,5,6,7,8};
        int[] result = instance.merge(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of grow method, of class ArrayUtil.
     */
    @Test
    public void testGrow() {
        System.out.println("grow");
        int[] oldArray = new int[]{1,2,3,4,5};
        int size = 5;
        ArrayUtil instance = new ArrayUtil();
        int[] expResult = new int[]{1,2,3,4,5,0,0,0,0,0};
        int[] result = instance.grow(oldArray, size);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of fibonacci method, of class ArrayUtil.
     */
    @Test
    public void testFibonacci() {
        System.out.println("fibonacci");
        int max = 15;
        ArrayUtil instance = new ArrayUtil();
        int[] expResult = new int[]{1,1,2,3,5,8,13};
        int[] result = instance.fibonacci(max);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getPrimes method, of class ArrayUtil.
     */
    @Test
    public void testGetPrimes() {
        System.out.println("getPrimes");
        int max = 23;
        ArrayUtil instance = new ArrayUtil();
        int[] expResult = new int[]{2,3,5,7,11,13,17,19};
        int[] result = instance.getPrimes(max);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getPerfectNumbers method, of class ArrayUtil.
     */
    @Test
    public void testGetPerfectNumbers() {
        System.out.println("getPerfectNumbers");
        int max = 10;
        ArrayUtil instance = new ArrayUtil();
        int[] expResult = new int[]{6};
        int[] result = instance.getPerfectNumbers(max);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin() {
        System.out.println("join");
        int[] array = new int[]{1,2,3,4,5};
        String seperator = "";
        ArrayUtil instance = new ArrayUtil();
        String expResult = "1-2-3-4-5";
        String result = instance.join(array, seperator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
