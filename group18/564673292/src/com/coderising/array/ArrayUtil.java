package com.coderising.array;

import java.util.Arrays;

public class ArrayUtil {

    // private function to swap elements in array
    private static void swap(int[] arr, int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin: the original array
     * @return null
     */
    public static void reverseArray(int[] origin){
        for (int i = 0; i < origin.length / 2; i++) {
            swap(origin, i, origin.length - i - 1);
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray: original array
     * @return new array with no zero-value elements
     */

    // do this with functional programming
    public static int[] removeZero(int[] oldArray){
         return Arrays.stream(oldArray).filter(value -> value != 0).toArray();
    }

    // insert one int element without ArrayList
    private static int[] insert(int[] arr, int index, int element){
        arr = grow(arr, 1);
        for (int i = arr.length - 1; i > index ; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        return arr;
    }

    // parameter checking for ArrayUtil.merge()
    private static boolean isSortedWithNoDuplicates(int[] arr){
        if(arr.length == 0){
            return true;
        }else{
            for (int i = 0; i < arr.length - 1; i++) {
                if(arr[i] >= arr[i + 1]){
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3,5,7,8]   a2 = [4,5,6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1: the first sorted array
     * @param array2: the second sorted array
     * @return a new array that:
     * is sorted
     * contains all elements from arr1 and arr2
     * with duplicates removed
     */

    // the algorithm with minimal loop count
    public static int[] merge(int[] array1, int[] array2){
        if(!isSortedWithNoDuplicates(array1) || !isSortedWithNoDuplicates(array2)){
            throw new IllegalArgumentException("Arrays should be sorted and contain no duplicates.");
        }
        int i = 0, j = 0, k = 0;
        int[] result = new int[0];
        while(i < array1.length && j < array2.length){
            int a = array1[i++];
            int b = array2[j++];
            result = grow(result, 1);
            if(a < b){
                result[k++] = a;
                j--;
            }else if(a > b) {
                result[k++] = b;
                i--;
            }else{
                result[k++] = a;
            }
        }
        while(i < array1.length){
            result = grow(result, 1);
            result[k++] = array1[i++];
        }
        while(j < array2.length){
            result = grow(result, 1);
            result[k++] = array2[j++];
        }
        return result;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     * @param oldArray: original array
     * @param size: the number of cells by which the array will grow
     * @return a new array with a capacity of oldArray.length + size
     */

    public static int[] grow(int [] oldArray,  int size){
        return Arrays.copyOf(oldArray, oldArray.length + size);
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     * @param max: the value that the last element of fibonacci array cannot be greater than it
     * @return a fibonacci array
     */

    public static int[] fibonacci(int max){
        if(max < 1){
            return new int[0];
        }
        int[] arr = {1};
        int last = 1;
        int i = 1;
        while(last <= max){
            arr = grow(arr, 1);
            arr[i] = last;
            last += arr[i - 1];
            i++;
        }
        return arr;
    }

    private static int currentPrime = 0;

    // determine if a number is a prime
    private static boolean isPrime(int x){
        if(x < 2){
            return false;
        }
        boolean isPrime = true;
        for (int i = x - 1; i > 1; i--) {
            if(x % i == 0){
                isPrime = false;
            }
        }
        return isPrime;
    }

    // the iterator to get prime
    private static int getPrime(){
        if(currentPrime == 0){
            currentPrime = 2;
        }else{
            while(true){
                int i = ++currentPrime;
                if(isPrime(i)){
                    currentPrime = i;
                    break;
                }
            }
        }
        return currentPrime;
    }

    // the function to reset prime iterator
    private static void clearPrime(){
        currentPrime = 0;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max: the value that the last element of prime array cannot be greater than it
     * @return a prime array
     */

    // the function to get prime array
    public static int[] getPrimes(int max){
        if(max < 2){
            return new int[0];
        }
        clearPrime();
        int[] result = new int[0];
        int prime;
        int i = 0;
        while((prime = getPrime()) <= max){
            result = grow(result,1);
            result[i++] = prime;
        }
        return result;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max : the value that the last element of perfectNumber array cannot be greater than it
     * @return a perfectNumber array
     */
    public static int[] getPerfectNumbers(int max){
        if(max < 6){
            return new int[0];
        }
        clearPrime();
        int[] result = new int[0];
        int i = 0;
        int perfectNumber;
        // using Euler's equation
        while(true){
            int p = getPrime();
            int q = (int)Math.pow(2,p) - 1;
            if(isPrime(q)){
                perfectNumber = (int)(q * Math.pow(2, p - 1));
                if(perfectNumber <= max){
                    result = grow(result, 1);
                    result[i++] = perfectNumber;
                }else{
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 用separator 把数组 array给连接起来
     * 例如array= [3,8,9], separator = "-"
     * 则返回值为"3-8-9"
     * @param array: the original array
     * @param separator: the char to link elements in array
     * @return string which is elements connected by separator
     */

    public static String join(int[] array, String separator){
        if(array.length == 0){
            return "";
        }
        String output = "";
        for (int i = 0; i < array.length; i++) {
            output += array[i] + separator;
        }
        output = output.substring(0,output.length() - 1);
        return output;
    }
}
