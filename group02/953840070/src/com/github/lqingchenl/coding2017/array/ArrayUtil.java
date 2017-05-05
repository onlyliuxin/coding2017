package com.github.lqingchenl.coding2017.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        int length = origin.length;

        int[] b = new int[length];
        for (int i = 0; i < length; i++) {
            b[i] = origin[i];
        }
        for (int i = 0; i < length; i++) {
            origin[length - i - 1] = b[i];
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     *
     * @param oldArray
     * @return
     */

    public int[] removeZero(int[] oldArray) {
        if (oldArray == null) {
            return null;
        }
        int length = oldArray.length;
        for (int i : oldArray) {
            if (i == 0) {
                length--;
            }
        }

        int[] newArray = new int[length];
        int j = 0;
        for (int i : oldArray) {
            if (i != 0) {
                newArray[j] = i;
                j++;
            }
        }
        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     *
     * @param array1
     * @param array2
     * @return
     */

    public int[] merge(int[] array1, int[] array2) {
        if (array1 == null || array1.length == 0) {
            return array2;
        }
        if (array2 == null || array2.length == 0) {
            return array1;
        }

        Map<Integer, Integer> map = new HashMap<>();


        int sameNum = 0;
        for (int i : array1) {
            for (int j : array2) {
                if (i == j) {
                    sameNum++;
                    map.put(i, 1);
                }
            }
        }

        int length = array1.length + array2.length - sameNum;
        int[] newArray = new int[length];
        int index = 0;

        for (int i : array1) {
            newArray[index] = i;
            index++;
        }
        for (int j : array2) {
            if (map.get(j) == null) {
                newArray[index] = j;
                index++;
            }
        }

        for (int i = 0; i < newArray.length - 1; i++) {
            for (int j = i + 1; j < newArray.length; j++) {
                if (newArray[i] > newArray[j]) {
                    int temp = newArray[j];
                    newArray[j] = newArray[i];
                    newArray[i] = temp;
                }
            }
        }

        return newArray;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     *
     * @param oldArray
     * @param size
     * @return
     */
    public int[] grow(int[] oldArray, int size) throws Exception {

        if (oldArray == null) {
            return null;
        }
        if (size < 0) {
            throw new Exception("12");
        }

        int newLength = oldArray.length + size;
        int[] newArray = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            if (i < oldArray.length) {
                newArray[i] = oldArray[i];
            } else {
                newArray[i] = 0;
            }

        }
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {
        if (max == 1) {
            return new int[0];
        }

        int index = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (getFibonacci(i) > max) {
                index = i;
                break;
            }
        }
        int[] array = new int[index];
        for (int i = 0; i < index; i++) {
            array[i] = getFibonacci(i);
        }
        return array;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        if (max == 1) {
            return new int[0];
        }

        int length = 0;
        for (int i = 1; i <= max; i++) {
            if (isPrime(i)) {
                length++;
            }
        }
        int[] array = new int[length];
        int j = 0;
        for (int i = 1; i <= max; i++) {
            if (isPrime(i)) {
                array[j] = i;
                j++;
            }
        }

        return array;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= max; i++) {
            int temp = 0;
            for (int n = 1; n < i / 2 + 1; n++) {
                if (i % n == 0) {
                    temp += n;
                }
            }
            if (temp == i) {
                stringBuffer.append(i).append("-");
            }
        }
        String[] strArray = stringBuffer.toString().split("-");
        int length = stringBuffer.toString().split("-").length;
        int[] intAarray = new int[length];
        for (int i=0; i<length; i++){
            intAarray[i] = Integer.parseInt(strArray[i]);
        }
        return intAarray;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param seperator
     * @return
     */
    public String join(int[] array, String seperator) {
        if (array==null || array.length==0){
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i<array.length; i++){
            stringBuffer.append(array[i]);
            if (i != array.length-1){
                stringBuffer.append(seperator);
            }
        }
        return stringBuffer.toString();
    }


    /**
     * 获取斐波那契数列
     *
     * @param i
     * @return
     */
    private int getFibonacci(int i) {
        if (i == 0 || i == 1) {
            return 1;
        }
        return getFibonacci(i - 2) + getFibonacci(i - 1);
    }

    /**
     * 判断是否为素数
     *
     * @param a
     * @return
     */
    private boolean isPrime(int a) {
        boolean flag = true;
        if (a < 2) {// 素数不小于2
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {// 若能被整除，则说明不是素数，返回false
                    flag = false;
                    break;// 跳出循环
                }
            }
        }
        return flag;
    }

}
