package org.pan.coding2017.array;

import java.util.Arrays;

/**
 * Created by QiPan on 2017/2/27.
 */
public class ArrayUtil {


    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin
     * @return
     */
    public static void reverseArray(int[] origin){

        // 如果是null, 或者长度小于等于1， 直接返回
        if (origin == null || origin.length <= 1){
            return;
        }
        for (int i = 0; i < origin.length/2; i++) {
            int tmp = origin[i];
            origin[i] = origin[origin.length - 1 - i];
            origin[origin.length - 1 - i] = tmp;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray
     * @return
     */

    public static int[] removeZero(int[] oldArray){

        if (oldArray == null){
            return oldArray;
        }
        int[] newArray = null;
        int count = 0; //统计被移出的数组的个数
        for (int i = 0; i < oldArray.length; i++) {
            int num = oldArray[i];
            if (num == 0){
                count++;
                System.arraycopy(oldArray, i+1, oldArray, i, oldArray.length - i - 1);
                i--;
            }
        }
        if (count == 0){
            newArray = oldArray;
        }else {
            newArray = new int [oldArray.length - count];
            System.arraycopy(oldArray, 0, newArray, 0 , oldArray.length - count);
        }
        return newArray;
    }

    /**
     * 不用JavaAPI来做
     * @param oldArray
     * @return
     */
    public static int[] removeZero_2(int[] oldArray){

        if (oldArray == null){
            return oldArray;
        }
        int count = 0;
        for (int num : oldArray) {
            if (num == 0){
                count ++;
            }
        }
        int[] newArray = new int[oldArray.length - count];
        for (int i = 0, j = 0; i < oldArray.length; i++, j++) {
            int num = oldArray[i];
            if (num == 0){
                j--;
            }else {
                newArray[j] = num;
            }
        }
        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */
    public static int[] merge(int[] array1, int[] array2){
        return  null;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     * @param oldArray
     * @param size
     * @return
     */
    public static int[] grow(int [] oldArray,  int size){
        if (oldArray == null){
            oldArray = new int[size];
        }
        oldArray = Arrays.copyOf(oldArray, oldArray.length + size);
        return oldArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     * @param max
     * @return
     */
    public int[] fibonacci(int max){
        return null;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
        return null;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        return null;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     * @param array
     * @param seperator
     * @return
     */
    public String join(int[] array, String seperator){
        if (array == null){
            return null;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1){

            }
        }
        return str.toString();
    }

}
