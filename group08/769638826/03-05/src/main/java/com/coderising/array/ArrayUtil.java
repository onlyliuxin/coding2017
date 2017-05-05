package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huitailang on 17/3/3.
 * 数组工具类
 */
public class ArrayUtil<T> {
    public static final int[] EMPTY_ARRAY = {};

    public static void reverseArray(int[] origin) {
        for (int i = 0; i < origin.length / 2; i++) {
            int tmp = 0;
            tmp = origin[i];
            origin[i] = origin[origin.length - i - 1];
            origin[origin.length - i - 1] = tmp;
        }
    }

    public static int[] removeZero(int[] oldArray) {

        int count = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                count++;
            }
        }

        int[] noZeroArray = new int[count];

        count = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                noZeroArray[count++] = oldArray[i];
            }
        }

        return noZeroArray;
    }

    public static int[] merge(int[] array1, int[] array2) {
        //1.合并
        int[] mergedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);

        //2.排序
        Arrays.sort(mergedArray);


        //3.去重
        Integer[] tmpArray = removeDuplicate(mergedArray);
        System.out.println(Arrays.toString(tmpArray));
        int[] resultArray = new int[tmpArray.length];
        for (int i = 0; i < tmpArray.length; i++) {
            resultArray[i] = tmpArray[i];
        }

        return resultArray;
    }

    private static Integer[] removeDuplicate(int[] origin) {
        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < origin.length; i++) {
            boolean repeat = false;
            for (int j = 0; j < arrayList.size(); j++) {
                if (origin[i] == arrayList.get(j)) {
                    repeat = true;
                    break;
                }
            }

            if (!repeat) {
                arrayList.add(origin[i]);
            }
        }

        return arrayList.toArray(new Integer[]{});
    }

    public static int[] grow(int[] oldArray, int size) {
        int[] newArray = new int[oldArray.length + size];
        int[] zeroArray = new int[size];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    public static int[] fibonacci(int max) {
        if (max == 1) {
            return EMPTY_ARRAY;
        }

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        int n = 1;
        while (true) {
            int ret = 0;
            if ((ret = fibonacci0(n)) > max) {
                break;
            } else {
                arrayList.add(ret);
            }

            n++;
        }

        int[] array = new int[arrayList.size()];
        int i = 0;
        for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) {
            Integer value = iterator.next();
            array[i++] = value;
        }

        return array;
    }

    private static int fibonacci0(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibonacci0(n - 1) + fibonacci0(n - 2);
        }
    }

    public static int[] getPrimes(int max) {
        ArrayList<Integer> primesList = new ArrayList<Integer>();

        for (int i = 0; i < max; i++) {
            if (isPrime(i)) {
                primesList.add(i);
            }
        }

        int[] primeArray = new int[primesList.size()];
        int i = 0;
        for (Iterator<Integer> iterator = primesList.iterator(); iterator.hasNext(); ) {
            Integer prime = iterator.next();
            primeArray[i++] = prime;
        }

        return primeArray;
    }

    private static boolean isPrime(int a) {
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

    public static int[] getPerfectNumbers(int max){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for(int i = 0; i < max; i++){
            if(isPerfectNumber(i)){
                arrayList.add(i);
            }
        }

        int[] array = new int[arrayList.size()];
        int i = 0;
        for(Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ){
            int perfectNumber = iterator.next();
            array[i++] = perfectNumber;
        }

        return array;
    }

    public static boolean isPerfectNumber(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum = sum + i;
            }
        }
        if (sum == n) {
            return true;
        } else
            return false;
    }

    public static String join(int[] array, String seperator){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < array.length; i++){
            sb.append(array[i]);
            sb.append(seperator);
        }

        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static <T> T[] convertListToArray(List<T> list){
        Object[] array = new Object[list.size()];

        int i = 0;
        for(Iterator<T> iterator = list.iterator(); iterator.hasNext(); ){
            T value = iterator.next();
            array[i] = (Object)value;
        }

        return (T[])array;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci0(4));
    }
}
