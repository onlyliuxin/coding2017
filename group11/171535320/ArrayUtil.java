import sun.security.util.Length;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by dengdechao on 2017/2/27.
 */
public class ArrayUtil {
    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin
     * @return
     */

    public static void reverseArray(int[] origin){
        if(origin == null) {
            return ;
        }
        int i = 0;
        int j = origin.length - 1;
        while(i != j) {
            int temp = origin[i];
            origin[i] = origin[j];
            origin[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray
     * @return
     */

    public static int[] removeZero(int[] oldArray){
        if(oldArray == null) {
            return null;
        }
        int i = 0;
        int j = oldArray.length - 1;

        while(i != j) {
            if(oldArray[i] != 0) {
                i++;
                continue;
            }
            if(oldArray[j] == 0) {
                j--;
                continue;
            }
            int temp = oldArray[i];
            oldArray[i] = oldArray[j];
            oldArray[j] = temp;
        }
        int[] array = new int[i];
        for(int n = 0; n < i; ++n) {
            array[n] = oldArray[n];
        }
        return array;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */

    public static int[] merge(int[] array1, int[] array2){
        int[] result = new int[array1.length + array2.length];
        int len1 = 0;
        int len2 = 0;
        int i = 0;
        while(len1 != array1.length || len2 != array2.length) {
            if(len1 < array1.length && len2 < array2.length) {
                if(array1[len1] < array2[len2]) {
                    result[i++] = array1[len1++];
                } else if(array1[len1] > array2[len2]) {
                    result[i++] = array2[len2++];
                } else {
                    result[i++] = array1[len1];
                    len1++;
                    len2++;
                }
            } else if(len1 < array1.length){
                result[i++] = array1[len1++];
            } else {
                result[i++] = array2[len2++];
            }

        }
        int[] last = new int[i];
        System.arraycopy(result,0,last,0,i);
        return last;
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
        int len = oldArray.length + size;
        int[] result = new int[len];
        for(int i = 0; i < oldArray.length; ++i) {
            result[i] = oldArray[i];
        }
        return result;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     * @param max
     * @return
     */
    public int[] fibonacci(int max){
        return null;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
        List<Integer> list = new ArrayList();

        for(int i = 2; i <= max; ++i) {
            int temp = 2;
            while(temp < i) {
                if(i % temp == 0) {
                    break;
                }
                ++temp;
            }
            if(i == temp) {
                list.add(i);
            }
        }

        int[] result = new int[list.size()];

        for(int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        List<Integer> list = new ArrayList();

        for(int j = 0; j < max; ++j) {
            int temp = 0;
            for(int i = 0; i < j / 2 + 1; ++i) {
                if(j % i == 0) {
                    temp += i;
                }
            }
            if(temp == j) {
                list.add(j);
            }
        }

        int[] result = new int[list.size()];

        for(int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     * @param array
     * @param s
     * @return
     */
    public String join(int[] array, String seperator){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < array.length; ++i) {
            str.append(array[i]);
            if(i + 1 < array.length) {
                str.append(seperator);
            }
        }
        String s = str.toString();
        return s;
    }
}
