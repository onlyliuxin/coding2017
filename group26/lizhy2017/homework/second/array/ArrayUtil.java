package second.array;

import java.util.Arrays;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin 整形数组
     */
    public static void reverseArray(int[] origin) {
        if (null == origin) return;
        for (int i = 0; i < origin.length / 2; i++) {
            int temp = origin[i];
            origin[i] = origin[origin.length - i - 1];
            origin[origin.length - i - 1] = temp;
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
        if (null == oldArray) return null;
        int size = oldArray.length;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] == 0) {
                System.arraycopy(oldArray, i + 1, oldArray, i, oldArray.length - i - 1);
                size--;
            }
        }
        int[] newArray = new int[size];
        System.arraycopy(oldArray, 0, newArray, 0, size);
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

    public static int[] merge(int[] array1, int[] array2) {
        if (null == array1 || null == array2) return null;
        int[] temp = new int[array1.length + array2.length];
        int i = 0, j = 0;
        if (array1.length >= array2.length) {
            while (i < array2.length) {
                if (array1[i] <= array2[i])
                    temp[i] = array1[i];
                else
                    temp[i] = array2[i];
                i++;
            }
            System.arraycopy(array1, i - 1, temp, 2 * i - 1, temp.length - 2 * i - 1);
        } else {
            while (j < array1.length - 1) {
                if (array1[j] <= array2[j]) {
                    temp[j] = array1[j];
                    if (array1[j + 1] > array2[j]) {
                        temp[j] = array2[j];
                    }
                } else
                    temp[j] = array2[j];
                j++;

            }
            System.arraycopy(array2, j - 1, temp, 2 * j - 1, temp.length - 2 * j - 1);
        }
        return temp;
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
        int oldCapacity = oldArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < size) {
            newCapacity = size;
        }
        if (newCapacity > 2147483639) {
            newCapacity = 2147483639;
        }
        return oldArray = Arrays.copyOf(oldArray, newCapacity);
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
        if (max <= 1)
            return new int[0];
        int[] temp = new int[max];
        temp[0] = 1;
        temp[1] = 1;
        int last = 1;
        int count = temp.length;
        while (last < max) {
            int x = temp[count - 1] + temp[count - 2];
            temp[count] = x;
            count++;
            last = x;
        }
        System.arraycopy(temp, count, temp, count - 1, max - count);
        return temp;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
//    public static int[] getPrimes(int max) {
//        int[] temp = new int[max];
//        if (max < 2)
//            return new int[0];
//        else if (max == 2)
//            return new int[]{2};
//        else {
//            temp[2] = 2;
//            int index = 3;
//            for (int i = 3; i <= max; i += 2) {
//                boolean flag = true;
//                for (int j = 2; j < i; j++) {
//                    if (i % j == 0) {
//                        flag = false;
//                    }
//                }
//                if (flag) {
//                    temp[index++] = i;
//                }
//            }
//            if (temp[temp.length - 2] >= max)
//                System.arraycopy(temp, temp.length - 1, temp, temp.length - 2, 1);
//        }
//
//        return temp;
//    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        int[] temp = new int[max];
        int index = 0;
        for (int i = 1; i <= max; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                temp[index++] = i;
            }
        }

        return temp;
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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                builder.append(array[i]);
            } else builder.append(array[i]).append(seperator);
        }
        return builder.toString();
    }


}
