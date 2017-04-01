package com.aaront.exercise;

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
        if (origin == null || origin.length == 0) return;
        int head = 0;
        int tail = origin.length - 1;
        while (head < tail) {
            origin[head] = origin[head] ^ origin[tail];
            origin[tail] = origin[head] ^ origin[tail];
            origin[head] = origin[head] ^ origin[tail];
            head++;
            tail--;
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
        if (oldArray == null) return new int[0];
        int size = oldArray.length;
        for (int i = 0; i < size; i++) {
            if (oldArray[i] != 0) continue;
            int j = i + 1;
            for (; j < size; j++) {
                if (oldArray[j] != 0) break;
            }
            size -= (j - i);
            move(oldArray, i, j - i, size);
        }

        int[] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    private void move(int[] array, int start, int moveLen, int size) {
        for (int i = start; i < size; i++) {
            array[i] = array[i + moveLen];
        }
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
        int len1 = array1.length;
        int len2 = array2.length;
        int[] mergeArray = new int[len1 + len2];
        int i = 0;
        int j = 0;
        int k = 0;
        for (; i < len1 && j < len2; ) {
            if (array1[i] < array2[j]) {
                mergeArray[k] = array1[i];
                i++;
            } else if (array1[i] > array2[j]) {
                mergeArray[k] = array2[j];
                j++;
            } else {
                mergeArray[k] = array1[i];
                i++;
                j++;
            }
            k++;
        }
        while (i < len1) {
            mergeArray[k++] = array1[i++];
        }
        while (j < len2) {
            mergeArray[k++] = array2[j++];
        }
        return resize(mergeArray, k);
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
        if (oldArray == null) return new int[0];
        int[] newArray = new int[oldArray.length + size];
        int index = 0;
        for (int len = oldArray.length; index < len; index++) {
            newArray[index] = oldArray[index];
        }
        for (int len = newArray.length; index < len; index++) {
            newArray[index] = 0;
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
        if (max <= 1) return new int[0];
        int[] elements = new int[10];
        elements[0] = 1;
        elements[1] = 1;
        int index = 1;
        int sum;
        while ((sum = elements[index] + elements[index - 1]) < max) {
            if (index + 1 >= elements.length) elements = dilatation(elements);
            elements[++index] = sum;
        }
        if (index < elements.length - 1) {
            elements = resize(elements, index + 1);
        }
        return elements;
    }

    private int[] dilatation(int[] origin) {
        int len = origin.length;
        int[] newArray = new int[len * 2];
        for (int i = 0; i < len; i++) {
            newArray[i] = origin[i];
        }
        return newArray;
    }

    private int[] resize(int[] origin, int size) {
        int[] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = origin[i];
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
    public int[] getPrimes(int max) {
        int[] primes = new int[max];
        int index = 0;
        for (int i = 2; i < max; i++) {
            if (isPrimes(i)) {
                primes[index++] = i;
            }
        }
        return resize(primes, index);
    }

    private boolean isPrimes(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        int[] perfectNumbers = new int[max];
        int index = 0;
        for (int i = 1; i < max; i++) {
            int[] factors = getFactors(i);
            int sum = 0;
            for (int factor : factors) {
                sum += factor;
            }
            if (sum == i) perfectNumbers[index++] = i;
        }
        return resize(perfectNumbers, index);
    }

    private int[] getFactors(int num) {
        int[] factors = new int[num];
        int index = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factors[index++] = i;
            }
        }
        return resize(factors, index);
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
        if (array == null) return "";
        StringBuilder sb = new StringBuilder("");
        for(int i = 0, len = array.length;i<len;i++) {
            sb.append(array[i]);
            if(i != len - 1) sb.append(seperator);
        }
        return sb.toString();
    }
}