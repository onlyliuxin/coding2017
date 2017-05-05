package xdx.homework.second.array;

import xdx.homework.first.ArrayList;
import xdx.homework.first.List;
import xdx.homework.first.Queue;
import xdx.homework.first.Stack;

import java.util.Arrays;

import static java.lang.StrictMath.sqrt;

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

        int end = origin.length - 1;
        for (int begin = 0; begin < end; begin++, end--) {
            // 交换首尾
            origin[begin] = origin[begin] ^ origin[end];
            origin[end] = origin[begin] ^ origin[end];
            origin[begin] = origin[begin] ^ origin[end];
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

        int[] newArray = new int[oldArray.length];
        int newArrayIndex = 0;
        int i = 0;
        for (; i < oldArray.length; i++) {
            if (oldArray[i] == 0) continue;
            newArray[newArrayIndex++] = oldArray[i];
        }
        return Arrays.copyOfRange(newArray, 0, newArrayIndex);
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

        // 分别放进两个栈中,取小的出栈
        Queue<Integer> queue1 = new Queue<>();
        Queue<Integer> queue2 = new Queue<>();
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int a : array1) {
            queue1.enQueue(a);
        }
        for (int a : array2) {
            queue2.enQueue(a);
        }
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (queue1.getFront() < queue2.getFront()) {
                newArray.add(queue1.deQueue());
            } else if (queue1.getFront() > queue2.getFront()) {
                newArray.add(queue2.deQueue());
            } else {
                newArray.add(queue2.deQueue());
                queue1.deQueue();
            }
        }
        while (!queue1.isEmpty()) newArray.add(queue1.deQueue());
        while (!queue2.isEmpty()) newArray.add(queue2.deQueue());

        int[] retArray = new int[newArray.size()];
        for (int i = 0; i < newArray.size(); i++) {
            retArray[i] = newArray.get(i);
        }
        return retArray;
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

        int[] newArray = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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

        int first = 1;
        int second = 1;
        if (max == 0) return new int[]{0};
        if (max == 1) return new int[]{first, second};

        List<Integer> fibList = new ArrayList<>();
        fibList.add(first);
        fibList.add(second);
        int last = first + second;
        while (last < max) {
            fibList.add(last);
            first = second;
            second = last;
            last = first + second;
        }

        return list2array(fibList);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {

        if (max < 2) return new int[]{};
        if (max == 2) return new int[]{2};

        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        for (int i = 3; i <= max; i += 2) {
            // 判断i是不是素数
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) isPrime = false;
            }
            if (isPrime) primeList.add(i);
        }

        return list2array(primeList);
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {

        if (max < 1) return new int[]{};

        List<Integer> perfectNums = new ArrayList<>();

        for (int aPerfectNum = 1; aPerfectNum <= max; aPerfectNum++) {
            int sumFactors = 1;
            for (int factor = 2; factor <= aPerfectNum / 2; factor++) {
                if (aPerfectNum % factor == 0) {
                    sumFactors += factor;
                }
            }
            if (sumFactors == aPerfectNum) perfectNums.add(aPerfectNum);
        }

        return list2array(perfectNums);
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

        StringBuilder stringBuilder = new StringBuilder();
        for (int a : array) {
            stringBuilder.append(a).append(seperator);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private int[] list2array(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

}
