package data_structure;

import org.junit.Test;
import basic.dataStructure.ArrayUtil;

import java.util.Arrays;

/**
 * @author : 温友朝
 * @date : 2017/4/5
 */
public class ArrayUtilTest {
    ArrayUtil au = new ArrayUtil();

    @Test
    public void testReverse(){
        int[] arr = {1, 2, 3, 4, 5};
        this.au.reverseArray(arr);
    }

    @Test
    public void testTrim(){
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int[] arr = this.au.removeZero(oldArr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testMerge(){
        int[] a1 = {3, 5, 7,8};
        int[] a2 = {4, 5, 6,7};

        int[] arr = this.au.merge(a1, a2);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGrow(){
        int[] arr = {1, 2, 3, 4, 5};
        int[] arr2 = this.au.grow(arr, 4);
        System.out.println(Arrays.toString(arr2));
    }

    @Test
    public void testFibonacci(){
        int[] arr = this.au.fibonacci(100);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testPrimes(){
        int[] arr = this.au.getPrimes(100000);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testPerfectNumbers(){
        int[] arr = this.au.getPerfectNumbers(10000);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testJoin(){
        int[] arr = this.au.getPerfectNumbers(10000);
        System.out.println(this.au.join(arr, "-"));
    }
}
