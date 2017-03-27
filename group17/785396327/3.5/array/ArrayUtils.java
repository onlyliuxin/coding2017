package array;

import list.ArrayList;
import list.List;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


/**
 * Created by william on 2017/2/27.
 */
public class ArrayUtils {

    public static void reserveArray(int[] src) {
        int begin = 0;
        int end = src.length - 1;
        while (begin < end) {
            swap(src, begin++, end--);
        }
    }

    public static int[] removeZero(int[] oldArray) {
        List<Integer> newResult = new ArrayList<Integer>();
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0)
                newResult.add(oldArray[i]);
        }

        return toIntArray(newResult);
    }

    public static int[] merge(int[] array1, int[] array2) {
        int[] temp = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, temp, 0, array1.length);
        System.arraycopy(array2, 0, temp, array1.length, array2.length);
        Arrays.sort(temp);
        List<Integer> result = new ArrayList<Integer>();
        for (int ele : temp) {
            if (!result.contains(ele))
                result.add(ele);
        }
        return toIntArray(result);
    }

    public static int[] grow(int[] oldArray, int size) {
        if (size <= 0)
            throw new NoSuchElementException();
        int[] result = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, result, 0, oldArray.length);
        return result;
    }

    public static int[] fibonacci(int max) {
        if (max <= 1)
            return new int[0];
        List<Integer> fList = new ArrayList<Integer>();
        fList.add(1);
        fList.add(1);
        int last = fList.size() - 1;
        while (fList.get(last) < max) {
            fList.add(fList.get(last) + fList.get(last - 1));
            last++;
        }
        return toIntArray(fList);
    }

    public static int[] getPrimes(int max) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            if (i % 2 == 1)
                result.add(i);
        }
        return toIntArray(result);
    }

    public static int[] getPerfectNumbers(int max) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i <= max; i++) {
            int sum = 0;
            for (int j = 1; j < i / 2 + 1; j++) {
                if (i % j == 0)
                    sum += j;
            }
            if (i == sum)
                result.add(i);
        }
        return toIntArray(result);
    }

    private static int[] toIntArray(List<Integer> src) {
        int[] result = new int[src.size()];
        for (int i = 0; i < src.size(); i++) {
            result[i] = src.get(i);
        }
        return result;
    }

    public static String join(int[] array, String seperator) {
        String value = Arrays.toString(array).replaceAll(", ", seperator == null ? "-" : seperator);
        return value.substring(1, value.length() - 1);
    }


    private static void swap(int[] array, int i, int j) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }

}
