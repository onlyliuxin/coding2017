package com.dudy.learn01.data_structure.base;

import com.dudy.learn01.data_structure.array.MyArrayUtil;
import org.junit.Test;

/**
 * <pre>
 *
 * </pre>
 *
 * @author dudy;
 * @version \$Id:  MyArrayUtilTest, v 1.0 2017/2/28 15:39 dudy Exp;
 */
public class MyArrayUtilTest {


    public  void  print(int desArr[]){
        for (int i = 0; i < desArr.length; i++) {
            System.out.print(desArr[i] + ",");
        }
    }

    public  void  print(Integer desArr[]){
        for (int i = 0; i < desArr.length; i++) {
            System.out.print(desArr[i] + ",");
        }
    }


    @Test
    public void testReverseArray() throws Exception {

       int origin[]  = new int[]{7, 9, 30, 3, 4};

        MyArrayUtil.reverseArray(origin);
        for (int i = 0; i <origin.length; i++) {
            System.out.print(origin[i] +" ,");
        }
    }

    @Test
    public void testRemoveZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};

        int[] result = MyArrayUtil.removeZero(oldArr);

        print(result);
    }

    @Test
    public void testMerge() throws Exception {

       int  a1[] =new int[] {3, 5, 7,8};
        int a2[] =new int[] {4, 5, 6,7};

        int[] a3 = MyArrayUtil.merge(a1, a2);
        print(a3);
    }

    @Test
    public void testGrow() throws Exception {

        int  a1[] =new int[] {3, 5, 7,8};
        int[] grow = MyArrayUtil.grow(a1, 3);
        print(grow);


    }

    @Test
    public void testFibonacci() throws Exception {

        Integer[] integers = MyArrayUtil.fibonacci(15);
        print(integers);
    }

    @Test
    public void testGetPrimes() throws Exception {

        Integer[] primes = MyArrayUtil.getPrimes(100);
        print(primes);


    }

    @Test
    public void testGetPerfectNumbers() throws Exception {

        Integer[] numbers = MyArrayUtil.getPerfectNumbers(100);
        print(numbers);
    }

    @Test
    public void testJoin() throws Exception {
        int  a1[] =new int[] {3, 5, 7,8};
        String join = MyArrayUtil.join(a1, "-");
        System.out.println(join);

    }
}