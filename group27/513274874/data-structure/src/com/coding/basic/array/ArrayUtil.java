package com.coding.basic.array;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public static void reverseArray(final int[] origin) {
        int size = origin.length;
        if (size <= 0) return;

        int[] newArray = copyOf(origin);

        for (int i = 0; i < size; i++) {
            origin[i] = newArray[size - 1 - i];
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
        int size = oldArray.length;
        int countZero = 0;
        //首先判断数组中0的个数
        for (int i : oldArray) {
            if (i == 0) countZero++;
        }
        int[] newArray = new int[size - countZero];
        //cur 命名newArray的游标
        int cur = 0;
        for (int i = 0; i < size; i++) {
            if (oldArray[i] == 0) continue;
            newArray[cur++] = oldArray[i];
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

    public static int[] merge(int[] array1, int[] array2) {
        //判断数组是否为空
        int size1 = array1.length;
        int size2 = array2.length;
        if (size1 <= 0 || size2 <= 0)
            return size1 <= 0 ? array2 : array1;

        //先将两个数组合并成一个数组
        int[] newArray = new int[size1 + size2];
        System.arraycopy(array1, 0, newArray, 0, size1);
        System.arraycopy(array2, 0, newArray, size1, size2);


        //对数组进行插入排序（假定array1已经是有序数组）
        int in, out;
        for (out = size1; out < newArray.length; out++) {
            in = out;
            int temp = newArray[out];

            while (in > 0 && newArray[in - 1] >= temp) {
                //右移
                newArray[in] = newArray[in - 1];
                --in;
            }
            newArray[in] = temp;
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
    public static int[] grow(int[] oldArray, int size) {
        int oldSize = oldArray.length;
        if (oldSize == 0) return new int[size];

        if (size <= 0) return oldArray;

        int[] newArray = new int[oldSize + size];
        System.arraycopy(oldArray, 0, newArray, 0, oldSize);

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
    public static int[] fibonacci(int max) {
        //先确定数组长度
        if (max == 1) return new int[]{};
        //这里的cur指的是数组的下标，从0开始，而不是数学函数1开始
        int cur = 2;
        int val_1 = 1;
        int val_2 = 1;
        while (val_1 + val_2 <= max) {
            int temp = val_1;
            val_1 = val_2;
            val_2 += temp;
            ++cur;
        }

        int[] newArray = new int[cur];
        for (int i = 0; i < cur; i++) {
            if (i == 0 || i == 1) {
                newArray[i] = 1;
                continue;
            }
            newArray[i] = newArray[i - 1] + newArray[i - 2];

        }
        return newArray;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        //先确定数组长度
        //判断质数循环
        int count = 0;
        for (int i = 1; i < max; i++) {
            //去掉偶数
            if (i == 1 || (i % 2 == 0 && i != 2)) continue;
            boolean flag = true;
            for (int j = 3; j <= Math.sqrt(i); j += 2) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        int[] newArray = new int[count];
        int cur = 0;
        for (int i = 1; i < max; i++) {
            //去掉偶数
            if (i == 1 || (i % 2 == 0 && i != 2)) continue;
            //判断到开根号即可
            boolean flag = true;
            for (int j = 3; j <= Math.sqrt(i); j += 2) {
                if (i % j == 0) {
                    flag = false;

                }
            }
            if (flag) {
                newArray[cur] = i;
                ++cur;
            }

        }


        return newArray;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        //求数组长度
        int count = 0;
        for (int a = 1; a <= max; a++) {
            int sum = 0;
            for (int i = 1; i <= a / 2; i++)
                if (a % i == 0)
                    sum += i;
            if (a == sum)
                ++count;
        }

        int[] newArray = new int[count];
        int cur = 0;
        for (int a = 1; a <= max; a++) {
            int sum = 0;
            for (int i = 1; i <= a / 2; i++)
                if (a % i == 0)
                    sum += i;
            if (a == sum) {
                newArray[cur] = a;
                ++cur;
            }
        }

        return newArray;
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
        int size = array.length;
        if (size == 0) return "";
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < size - 1; i++) {
            sb.append(array[i]).append(seperator);
        }
        sb.append(array[size - 1]);
        return sb.toString();
    }


    /**
     * 类私有函数，复制返回一个新的数组
     */
    private static int[] copyOf(int[] source) {
        int size = source.length;
        if (size <= 0) return null;

        int[] newArray = new int[size];
        //int[] ints = Arrays.copyOf(origin, size);
        System.arraycopy(source, 0, newArray, 0, size);
        return newArray;
    }


}
