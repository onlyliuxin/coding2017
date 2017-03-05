package com.coderising.array;


import com.coding.basic.ArrayList;
import com.coding.basic.Stack;

import java.util.HashSet;
import java.util.Iterator;

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
        if (isEmptyOrNull(origin)) {
            return;
        }
        //solution 1  move element
        for (int i = 0; i < origin.length >> 1; i++) {
            int temp = origin[i];
            origin[i] = origin[origin.length - 1 - i];
            origin[origin.length - 1 - i] = temp;
        }

        //solution 2 use Stack
//        Stack<Integer> stack = new Stack<>();
//        for (int i : origin) {
//            stack.push(i);
//        }
//        for (int i = 0; i < origin.length; i++) {
//            origin[i] = stack.pop();
//        }
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
        if (isEmptyOrNull(oldArray)) {
            return null;
        }
        //solution 1 use Queue OR Stack
        //Queue<Integer> queue = new Queue<>();
        //for (int i : oldArray) {
        //    if (i != 0) {
        //        queue.enQueue(i);
        //    }
        //}
        //int[] newArray = new int[queue.size()];
        //for (int i = 0; i < newArray.length; i++) {
        //    newArray[i] = queue.deQueue();
        //}
        //return newArray;


        //solution 2 use Array
        int[] tempArray = new int[oldArray.length];
        int index = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                tempArray[index++] = oldArray[i];
            }
        }
        int[] newArray = new int[index];
        System.arraycopy(tempArray, 0, newArray, 0, index);
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
        if (isEmptyOrNull(array1) && isEmptyOrNull(array2)) {
            return null;
        }
        if (isEmptyOrNull(array1)) {
            return array2;
        }
        if (isEmptyOrNull(array2)) {
            return array1;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i : array1) {
            set.add(i);
        }
        for (int i : array2) {
            set.add(i);
        }
        Iterator iterator = set.iterator();
        int[] result = new int[set.size()];
        int index = 0;
        while (iterator.hasNext()) {
            result[index++] = (int) iterator.next();
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i] > result[j]) {
                    int temp = result[i];
                    result[i] = result[j];
                    result[j] = temp;
                }
            }
        }
        return result;
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
    public int[] grow(int[] oldArray, int size) {
        if (isEmptyOrNull(oldArray)) {
            return null;
        }
        int[] newArray = new int[oldArray.length + size];
        //solution 1 use System.arraycopy
        //System.arraycopy(oldArray,0,newArray,0, oldArray.length);

        //solution 2 use loop
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
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
        int[] fibo = {};
        if (max <= 1) {
            return fibo;
        }
        ArrayList<Integer> fiboList = new ArrayList<>();
        fiboList.add(1);
        fiboList.add(1);
        if (max == 2) {
            fibo = new int[2];
            fibo[0] = 1;
            fibo[1] = 1;
            return fibo;
        }
        while (true) {
            int nextNum = fiboList.get(fiboList.size() - 1) + fiboList.get(fiboList.size() - 2);
            if (nextNum < max) {
                fiboList.add(nextNum);
            } else {
                break;
            }
        }
        fibo = new int[fiboList.size()];
        for (int i = 0; i < fibo.length; i++) {
            fibo[i] = fiboList.get(i);
        }

        return fibo;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int[] primes = {};
        if (max <= 2) {
            return primes;
        }
        ArrayList<Integer> primeList = new ArrayList<>();
        primeList.add(2);// first prime
        for (int i = 3; i < max; i++) {
            primeList.add(i);
        }
        int dividend = 2;
        while (true) {
            int index = primeList.indexOf(dividend);
            if (primeList.get(primeList.size() - 1).equals(dividend)) {
                break;
            }
            com.coding.basic.Iterator iterator = primeList.iterator();
            for (int i = 0; i <= index; i++) {
                iterator.next();//
            }
            while (iterator.hasNext()) {
                int i = (int) iterator.next();
                if (i % dividend == 0) {
                    iterator.remove();
                }
            }
            dividend = primeList.get(++index);
        }

        primes = new int[primeList.size()];
        for (int i = 0; i < primes.length; i++) {
            primes[i] = primeList.get(i);
        }
        return primes;
    }


    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i < max; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                arrayList.add(i);
            }
        }
        int[] perfectNumbers = new int[arrayList.size()];
        for (int i = 0; i < perfectNumbers.length; i++) {
            perfectNumbers[i] = arrayList.get(i);
        }
        return perfectNumbers;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param seperator 分隔符
     * @return
     */
    public String join(int[] array, String seperator) {
        if (isEmptyOrNull(array)) {
            return "";
        }
        if (array.length < 2) {
            return array[0] + "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < array.length - 1; i++) {
            stringBuffer.append(array[i] + seperator);
        }
        stringBuffer.append(array[array.length - 1]);
        return stringBuffer.toString();
    }

    /**
     * 检查数组是否为空或者为null
     *
     * @param array
     * @return
     */
    private boolean isEmptyOrNull(int[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }
}