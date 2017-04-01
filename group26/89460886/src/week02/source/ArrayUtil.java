package coding;

import java.util.Arrays;

/**
 * @author jiaxun
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        if (origin == null) return;
        int length = origin.length;
        for (int i = 0, middle = length / 2; i < middle; i++) {
            int temp = origin[i];
            origin[i] = origin[length - i - 1];
            origin[length - i - 1] = temp;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray
     * @return
     */

    public int[] removeZero(int[] oldArray) {
        if (oldArray == null) return null;
        int[] newArray = new int[oldArray.length];
        int count = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                newArray[count++] = oldArray[i];
            }
        }
        return Arrays.copyOf(newArray, count);
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */

    public int[] merge(int[] array1, int[] array2){
        int len1 = array1.length;
        int len2 = array2.length;
        int[] array3 = new int[len1 + len2];
        int arr1Index = 0, arr2Index = 0, arr3Index = 0, zeroCount = 0;
        while (arr1Index < len1 && arr2Index < len2) {
            if (array1[arr1Index] < array2[arr2Index]) {
                array3[arr3Index++] = array1[arr1Index++];
            } else if (array1[arr1Index] > array2[arr2Index]) {
                array3[arr3Index++] = array2[arr2Index++];
            } else {
                array3[arr3Index++] = array1[arr1Index++] = array2[arr2Index++];
                zeroCount++;
            }
        }
        while (arr1Index < len1) {
            array3[arr3Index++] = array1[arr1Index++];
        }
        while (arr2Index < len2) {
            array3[arr3Index++] = array2[arr2Index++];
        }
        return Arrays.copyOf(array3, len1 + len2 - zeroCount);
    }
    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     * @param oldArray
     * @param size
     * @return
     */
    public int[] grow(int [] oldArray,  int size){
        return Arrays.copyOf(oldArray, oldArray.length + size);
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     * @param max
     * @return
     */
    public int[] fibonacci(int max){
        if (max == 1) return null;
        int first = 1, second = 1;
        int next = 2;
        int[] result = new int[max];
        result[0] = first; result[1] = second;
        int count = 2;
        while (next < max) {
            result[count++] = next;
            first = second;
            second = next;
            next = first + second;
        }
        return Arrays.copyOf(result, count);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
        if (max <= 2) return null;
        int [] result = new int[max];
        int count = 0;
        for (int i = 2; i < max; i++) {
            if (!primes(i)) {
                result[count++] = i;
            }
        }
        return Arrays.copyOf(result, count);
    }

    private boolean primes(int data) {
        int sqrt = (int) Math.sqrt(data) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (data % i == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        int[] result = new int[max - 2];
        int count = 0;
        for (int i = 2; i < max; i++) {
            if (perfectNumber(i))
                result[count++] = i;
        }
        return Arrays.copyOf(result, count);
    }

    private boolean perfectNumber(int number) {
        int left = 2, right = number;
        int sum = 1;
        while (left < right) {
            if (number % left == 0) {
                sum += left;
                right = number / left;
                if (left != right) sum += right;
            }
            left++;
        }
        return sum == number;
    }

    /**
     * 用separator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     * @param array
     * @param separator
     * @return
     */
    public String join(int[] array, String separator){
        if (array == null) return null;
        StringBuilder result = new StringBuilder();
        result.append(array[0]);
        for (int i = 1, len = array.length; i < len; i++) {
            result.append(separator).append(array[i]);
        }
        return result.toString();
    }

}
