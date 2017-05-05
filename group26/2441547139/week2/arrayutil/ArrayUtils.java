package week2.arrayutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zndbl on 2017/3/23.
 */
public class ArrayUtils {

    public static void main(String[] args) {
//        int[] oldArray = new int[]{4,6,2,1,0,5,0,8};
//        int[] newArray = reverseArray(oldArray);
//        int[] newArray = removeZero(oldArray);
//        String array = "[";
//        for(int i = 0 ; i < newArray.length ; i++) {
//            array += newArray[i];
//        }
//        array += "]";
//        System.out.println(array);
//        String s = seperatorArray(oldArray);
//        System.out.println(s);
//        List math = getAllMath(6);
//        int[] array = getPrimeArray(23);
//        printArray(array);
        getFibonacci(15);
    }

    public static void printArray(int[] newArray) {
        String array = "[";
        for (int i = 0; i < newArray.length; i++) {
            array += (newArray[i]+",");
        }
        array += "]";
        System.out.println(array);
    }

    /**
     * 数组的反转
     *
     * @param oldArray
     * @return
     */
    public static int[] reverseArray(int[] oldArray) {
        int[] newArray = new int[oldArray.length];
        for (int i = 0; i < (oldArray.length); i++) {
            newArray[i] = oldArray[oldArray.length - 1 - i];
        }
        return newArray;
    }

    /**
     * 清除数组中的元素0
     *
     * @param array
     * @return
     */
    public static int[] removeZero(int[] array) {
        int[] newArray = new int[array.length];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                newArray[j++] = array[i];
            }
        }
        System.out.println(j);
        Arrays.copyOf(newArray, j);
        return newArray;
    }

    /**
     * 连接字符
     *
     * @param oldArray
     * @return
     */
    public static String seperatorArray(int[] oldArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < oldArray.length; i++) {
            if (i == oldArray.length - 1) {
                sb.append(oldArray[i]);
                break;
            }
            sb.append(oldArray[i]).append("_");
        }
        return sb.toString();
    }

    /**
     * 传100，求小于100的所有完数
     *
     * @param old
     * @return
     */
    public static List getAllMath(int old) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for (int i = 1; i <= old - 1; i++) {
            if (old % i == 0) {
                System.out.println(i);
                count = count + i;
                list.add(i);
            }
        }
        if (count == old) {
            return list;
        }
        return new ArrayList();
    }

    /**
     * 返回所有小于给定数的素数数组
     *
     * @param old
     * @return
     */
    public static int[] getPrimeArray(int old) {
        int[] primeArray = new int[old];
        int k = 0;
        for (int i = 1; i < old; i++) {
            if (isPrime(i)) {
                primeArray[k] = i;
                k++;
            }
        }
        return Arrays.copyOf(primeArray, k );
    }

    /**
     * 判断一个数是不是素数
     *
     * @param i
     * @return
     */
    public static boolean isPrime(int i) {
        int count = 0;
        for (int j = 1; j <= i; j++) {
            if (i % j == 0) {
                count++;
            }
        }
        if (count > 2 || count == 1) {
            return false;
        }
        return true;
    }

    /**
     * 小于给定数的斐波那契数列
     * 传进去15，1 1 2 3 5 8 13
     * @param old
     * @return
     */
    public static void getFibonacci(int old) {
        int first = 1;
        int second = 1;
        int num = add(first, second, old);
        System.out.println(num);
    }

    public static int add(int first, int second, int old) {
        int last = first + second;
        int before = second;
        if(last > old) {
            return before;
        }
        return add(before, last, old);
    }


}
