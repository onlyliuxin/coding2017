package task2.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int length = origin.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[i] = origin[length - i - 1];
        }
        System.arraycopy(temp, 0, origin, 0, length);
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
        int count = 0;
        int length = oldArray.length;
        for (int i = 0; i < length; i++) {
            int element = oldArray[i];
            if (element != 0) {
                oldArray[count] = element;
                count++;
            }
        }
        return Arrays.copyOf(oldArray, count);
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
        int firstPos = 0;
        int secondPos = 0;
        int firstLength = array1.length;
        int secondLength = array2.length;
        int[] tempArray = new int[firstLength + secondLength];
        int tempArrayPos = 0;
/*        比较两个数组第一个元素的大小,小的元素放到tempArray中且数组索引加一,然后继续比较.
        直到有一个数组遍历完,把另外一个数组剩下的元素依次放到tempArray中*/
        while (firstPos < firstLength && secondPos < secondLength) {
            int firstElement = array1[firstPos];
            int secondElement = array2[secondPos];
            if (firstElement < secondElement) {
                tempArray[tempArrayPos++] = array1[firstPos++];
            } else if (firstElement > secondElement) {
                tempArray[tempArrayPos++] = array2[secondPos++];
            } else {
                tempArray[tempArrayPos++] = array1[firstPos++];
                secondPos++;
            }
        }
        if (firstPos == firstLength && secondPos < secondLength) {
            System.arraycopy(array2, secondPos, tempArray, tempArrayPos, secondLength - secondPos);
            tempArrayPos += secondLength - secondPos;
        } else if (secondPos == secondLength && firstPos < firstLength) {
            System.arraycopy(array1, firstPos, tempArray, tempArrayPos, firstLength - firstPos);
            tempArrayPos += firstLength - firstPos;
        }
        return Arrays.copyOf(tempArray, tempArrayPos);
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
        int[] temp = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, temp, 0, oldArray.length);
        return temp;
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
        if (max == 1)
            return new int[0];
        int[] result = new int[max + 1];
        result[0] = 1;
        result[1] = 1;
        return getFibonacci(result, 2, max);
    }

    private int[] getFibonacci(int[] arr, int index, int max) {
        if (arr[index - 1] >= max) {
            return Arrays.copyOf(arr, index - 1);
        }
        arr[index] = arr[index - 1] + arr[index - 2];
        return getFibonacci(arr, ++index, max);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        if (max == 2)
            return new int[]{2};
        else if (max >= 3 && max <= 4)
            return new int[]{2, 3};
        else if (max > 4) {
            int[] arr = new int[max];
            arr[0] = 2;
            arr[1] = 3;
            int pos = 2;
            for (int i = 5; i < max; i++) {
                //去掉能被2、3整除的数:6n+2 6n+3 6n+4
                if ((i + 1) % 6 == 0 || (i - 1) % 6 == 0) {
                    if (isPrime(i)) {
                        arr[pos++] = i;
                    }
                }
            }
            return Arrays.copyOf(arr, pos);
        }
        return null;
    }

    private boolean isPrime(int number) {
        //去掉偶数
        if (number % 2 == 0)
            return false;
        //只能被自身整除的为素数
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
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
        int[] arr = new int[max];
        int pos = 0;
        for (int i = 4; i < max; i++) {
            //去掉素数
            if (!isPrime(i)) {
                List<Integer> list = new ArrayList<>();
                for (int j = 1; j < i; j++) {
                    if (i % j == 0)
                        list.add(j);
                }
                if (i == list.stream().reduce(0, (a, b) -> a + b))
                    arr[pos++] = i;
            }
        }
        return Arrays.copyOf(arr, pos);
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
        StringBuilder sb = new StringBuilder();
        for (int element : array) {
            sb.append(element).append(separator);
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}