package cn.cs.week2.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YCS on 2017/3/17.
 */
public class ArrayUtilTest {
  @Test
  public void test(){
    ArrayUtil util = new ArrayUtil();
    int[] arr = new int[]{1,3,4,6,2};
    //reverseArray()
    util.reverseArray(arr);
    Assert.assertEquals(arr[0],2);
    Assert.assertEquals(arr[1],6);
    Assert.assertEquals(arr[4],1);
    //removeZero()
    int[] arr1 = new int[]{1,2,3,0,0,3,2,0,1,0,9,0};
    int[] result1 = util.removeZero(arr1);
    Assert.assertEquals(result1[0],1);
    Assert.assertEquals(result1[3],3);
    Assert.assertEquals(result1[5],1);
    Assert.assertEquals(result1.length,7);
    //merge()
    int[] arr2 = new int[]{1,3,5,7};
    int[] arr3 = new int[]{1,2,3,4,6,7,9};
    int[] result2 = util.merge(arr2,arr3);
    Assert.assertEquals(result2.length,8);
    Assert.assertEquals(result2[5],6);
    //grow()
    int[] result3 = util.grow(arr2,2);
    Assert.assertEquals(result3[3],7);
    Assert.assertEquals(result3[5],0);
    //fibonacci()
    //15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
    int[] result4 = util.fibonacci(14);
    Assert.assertEquals(result4.length,7);
    Assert.assertEquals(result4[2],2);
    Assert.assertEquals(result4[6],13);
    //getPrimes()
    //max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
    int[] result5 = util.getPrimes(23);
    Assert.assertEquals(result5.length,8);
    Assert.assertEquals(result5[3],7);
    Assert.assertEquals(result5[7],19);
    //getPerfectNumbers()
    int[] result6 = util.getPerfectNumbers(29);
    Assert.assertEquals(result6[0],6);
    Assert.assertEquals(result6[1],28);
    //join()
    String s = util.join(arr2,"#");
    Assert.assertEquals(s,"1#3#5#7");
  }
}
