package com.coderising.array;

import java.util.ArrayList;

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
        if (null == origin) {
            return;
        }
        int first = 0;
        int last = origin.length - 1;
        int temp;
        while (first < last) {
            temp = origin[first];
            origin[first] = origin[last];
            origin[last] = temp;
            first++;
            last++;
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
        int[] resultArr = null;
        if (null == oldArray) {
            return resultArr;
        }
        int zeroSize = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] == 0) {
                zeroSize++;
            }
        }
        resultArr = new int[oldArray.length - zeroSize];
        int resultIndex = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                resultArr[resultIndex] = oldArray[i];
                resultIndex++;
            }
        }
        return resultArr;
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
        if (null == array1) {
            return array2;
        }
        if (null == array2) {
            return array1;
        }
        int[] resultArr = new int[array1.length + array2.length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < resultArr.length; i++) {
            if (array1[index1] < array2[index2]) {
                resultArr[i] = array1[index1];
                index1++;
            } else {
                resultArr[i] = array2[index2];
                index2++;
            }
        }
        return resultArr;
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
        if (oldArray == null) {
            return new int[size];
        }
        int[] resultArr = new int[oldArray.length + size];
        for (int i = 0; i < oldArray.length; i++) {
            resultArr[i] = oldArray[i];
        }
        return resultArr;
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
        ArrayList<Integer> tempList = new ArrayList<>();
        int resultSize = 0;
        while (fibonacci(tempList, resultSize) < max) {
            resultSize++;
        }
        int[] resultArr = new int[resultSize];
        for (int i = 0; i < resultSize; i++) {
            resultArr[i] = tempList.get(i);
        }
        return resultArr;
    }

    /**
     * 返回第index个斐波那契数列，resultArr用来存已经计算过的结果
     *
     * @param resultArr
     * @param index
     * @return
     */
    private int fibonacci(ArrayList<Integer> resultArr, int index) {
        if (resultArr.size() > index) {
            return resultArr.get(index);
        }
        int newResult;
        if (index == 0) {
            newResult = 1;
        } else if (index == 1) {
            newResult = 1;
        } else {
            newResult = (resultArr.get(index - 1) == 0 ? resultArr.get(index - 1) : fibonacci(resultArr, index - 1))
                    + (resultArr.get(index - 2) == 0 ? resultArr.get(index - 2) : fibonacci(resultArr, index - 2));
        }
        resultArr.add(newResult);
        return newResult;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        if (max < 2) {
            return null;
        }
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPrimes(tempList, i)) {
                tempList.add(i);
            }
        }
        int[] resultArr = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            resultArr[i] = tempList.get(i);
        }
        return resultArr;
    }

    private boolean isPrimes(ArrayList<Integer> primesList, int temp) {
        if (temp == 2 || temp == 3) {
            return true;
        }
        for (int i = 0; i < primesList.size(); i++) {
            if (temp % primesList.get(i) == 0) {
                return false;
            }
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
        if (max < 0) {
            return null;
        }
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPerfectNumber(i)) {
                tempList.add(i);
            }
        }
        int[] resultArr = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            resultArr[i] = tempList.get(i);
        }
        return resultArr;
    }

    private boolean isPerfectNumber(int temp) {
        if (temp == 1) {
            return true;
        }
        int sum = 0;
        for (int i = 1; i < temp; i++) {
            if (temp % 1 == 0) {
                if ((sum = sum + i) > temp) {
                    return false;
                }
            }
        }
        if (sum == temp) {
            return true;
        }
        return false;
    }


    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param separator
     * @return
     */
    public String join(int[] array, String separator) {
        if (null == array || array.length == 0 || null == separator) {
            return null;
        }
        StringBuilder sb = new StringBuilder(array.length * (1 + separator.length()));
        int i = 1;
        sb.append(array[0]);
        for (; i < array.length; i++) {
            sb.append(separator);
            sb.append(array[i]);
        }
        return sb.toString();
    }

}
