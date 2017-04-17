package basic.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by zhouliang on 2017-03-13.
 */
public class ArrayUtilTest {
    private int[] array;
    private ArrayUtil arrayUtil ;
    private int SIZE = 11;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
        array = new int[SIZE];
        Random random = new Random();

        for(int i=0; i<array.length; i++){
            array[i] = random.nextInt(100);
        }
       /* System.out.println("原数组");
        for(int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();*/
    }

    @Test
    public void reverseArray() throws Exception {
        System.out.println("转置数组");
        arrayUtil.reverseArray(array);
        for(int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        System.out.println("去掉0的数组");
        int[] news = arrayUtil.removeZero(oldArr);
        for(int i=0; i<news.length; i++){
            System.out.print(news[i]+" ");
        }
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7,8,9,9};
        int[] a2 = {4, 5, 6,7};
        int[] a3 = {3,4,5,6,7,8,8,8,8,8,8};
        int[] arr = arrayUtil.merge(a1,a2);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    @Test
    public void grow() throws Exception {
        int[] result = arrayUtil.grow(array,5);
        Assert.assertEquals(array.length+5,result.length);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
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