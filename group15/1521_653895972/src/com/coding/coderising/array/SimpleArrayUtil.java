package com.coding.coderising.array;

public class SimpleArrayUtil {

    private static void checkNull(int[] array) {
        if (array == null)
            throw new NullPointerException("array is null");
    }

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public static void reverseArray(int[] origin) {
        checkNull(origin);
        int i = 0;
        for (int j = origin.length - 1; j > i; ++i) {
            int tmp = origin[i];
            origin[i] = origin[j];
            origin[j] = tmp;
            --j;
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
        checkNull(oldArray);
        if (0 == oldArray.length)
            return oldArray;
        int[] temp = new int[oldArray.length];
        int index = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (0 != oldArray[i]) {
                temp[index++] = oldArray[i];
            }
        }
        int[] tem2 = new int[index];

        System.arraycopy(temp, 0, tem2, 0, Math.min(oldArray.length, index));
        return tem2;
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
        if (array1 == null && array2 == null)
            return null;
        if (array1.length == 0)
            return array2.clone();
        if (array2.length == 0)
            return array1.clone();
        //判断排序方向
        boolean sort = array1[0] < array1[array1.length - 1];
        //去重
        for (int i = 0; i < array2.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array1.length; j++) {
                if (array2[i] == array1[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //扩容
                array1 = grow(array1, 1);
                array1[array1.length - 1] = array2[i];
            }
        }
        //排序
        if (sort) {//小到大
            int tmp;
            //冒泡排序
            for (int i = 0; i < array1.length; i++) {
                for (int j = i + 1; j < array1.length; j++) {
                    if (array1[i] > array1[j]) {
                        tmp = array1[i];
                        array1[i] = array1[j];
                        array1[j] = tmp;
                    }
                }
            }
        } else {//大到小
            int tmp;
            //冒泡排序
            for (int i = 0; i < array1.length; i++) {
                for (int j = i + 1; j < array1.length; j++) {
                    if (array1[i] < array1[j]) {
                        tmp = array1[i];
                        array1[i] = array1[j];
                        array1[j] = tmp;
                    }
                }
            }
        }
        return array1;
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
        int[] newArr = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, newArr, 0, oldArray.length);
        return newArr;
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
        int[] arr = new int[]{};
        int i = 1;
        while (true) {
            int a = fb(i);
            if (a > 15)
                break;
            //扩容
            arr = grow(arr, 1);
            arr[i - 1] = a;
            i++;
        }
        return arr;
    }

    /**
     * 获取斐波那契数
     * F(0)=1，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
     */
    public static int fb(int i) {
        i = i < 1 ? 1 : i;
        if (i == 1 || i == 2) {
            return 1;
        }
        return fb(i - 1) + fb(i - 2);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        int[] arr = new int[]{2};
        if (max < 1) throw new IllegalArgumentException("不是大于1的自然数");
        int j = 1;
        for (int n = 3; n < max; n++) {
            int k = 2;
            while (n > k) {
                if (n % k == 0)
                    break;
                k++;
            }
            //扩容
            arr = grow(arr, 1);
            arr[j++] = n;
        }
        return arr;
    }


    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        int[] arr = new int[0];
        int a = 0;
        for (int i = 1; i < max; i++) {
            if (isPerfect(i)) {
                //扩容
                arr = grow(arr, 1);
                arr[a++] = i;
            }
        }
        return arr;
    }

    //判断 “完数”
    public static boolean isPerfect(int max) {
        int i = 1;
        int n = 0;
        while (i < max) {
            if (max % i == 0) {
                n += i;
            }
            i++;
        }
        if (n == max)
            return true;
        return false;
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
        if (array == null)
            return null;
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s += array[i];
            if (i != array.length - 1)
                s += seperator;
        }
        return s;
    }


}
