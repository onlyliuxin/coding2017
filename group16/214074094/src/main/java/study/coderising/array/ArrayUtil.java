package study.coderising.array;


import study.coding.basic.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public static void reverseArray(int[] origin) {
        int tmp;
        for (int i = 0; i < origin.length / 2; i++) {
            tmp = origin[i];
            origin[i] = origin[origin.length - i - 1];
            origin[origin.length - i - 1] = tmp;
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

    public static int[] removeZero(int[] oldArray) {
        int[] newArray = new int[oldArray.length];
        int k = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (0 != oldArray[i]) {
                newArray[k++] = oldArray[i];
            }
        }
        return Arrays.copyOf(newArray, k);
    }

    /**
     * 给定两个已经排序好的整形数组，a1和a2，创建一个新的数组a3，使得a3包含a1和a2的所有元素，并且仍然是有序的
     * 例如 a1 = {3, 5, 7, 8}，a2 = {4, 5, 6, 7}，则 a3 为[3,4,5,6,7,8]，注意：已经消除了重复
     *
     * @param a1
     * @param a2
     * @return
     */

    public static int[] merge(int[] a1, int[] a2) {
        Set set = new HashSet();

        for (int i = 0; i < a1.length + a2.length; i++) {
            if (i < a1.length) {
                set.add(a1[i]);
            } else {
                set.add(a2[i - a1.length]);
            }
        }

        int[] a3 = new int[set.size()];

        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            a3[i++] = (Integer) it.next();
        }
        return a3;
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
    public static int[] grow(int[] oldArray, int size) {
        return Arrays.copyOf(oldArray, oldArray.length + size);
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public static int[] fibonacci(int max) {
        if (max == 1) {
            return new int[0];
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        for (int i = 0; ; i++) {
            int tmp = list.get(i) + list.get(i + 1);
            if (tmp >= max) {
                break;
            }
            list.add(i + 2, tmp);
        }

        return convertIntegerArray2Int(list);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        if (max < 2) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return convertIntegerArray2Int(list);
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        if (max < 2) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();

        int count = 0;
        //如果p是质数，且2^p-1也是质数，那么（2^p-1）X2^（p-1）便是一个完全数
        for (int i = 2; i <= max / 2; i++) {
            count++;
            if (isPrime(i) && isPrime((int) Math.pow(2, i) - 1)) {
                System.out.println("count " + i + ":" + (int) (Math.pow(2, i) - 1) + " * " + (int) Math.pow(2, (i - 1)));
                int perfectNum = (int) ((Math.pow(2, i) - 1) * Math.pow(2, (i - 1)));
                if (perfectNum > max) {
                    break;
                }
                if (perfectNum < max) {
                    list.add(perfectNum);
                }
            }
        }
        System.out.println("total count : " + count);
        return convertIntegerArray2Int(list);
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
    public static String join(int[] array, String seperator) {
        if (null == array || array.length < 1) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(seperator);
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        for (int j = 2; j <= Math.sqrt(n); j++) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] convertIntegerArray2Int(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i).intValue();
        }
        return arr;
    }


}
