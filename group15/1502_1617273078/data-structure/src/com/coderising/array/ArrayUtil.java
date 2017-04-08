package com.coderising.array;

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
        int[] a = new int[origin.length];
        for (int i = 0; i < origin.length; i++) {
            a[i] = origin[origin.length - 1 - i];
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);

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
        int count = 0;
        int index = 0;
        //int[] brige = new int[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {

                count++;
            }
        }
        int[] result = new int[count];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                result[index++] = oldArray[i];
            }


        }
        return result;
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
        int alength = array1.length;
        int blength = array2.length;
        int[] newint = new int[alength + blength];
        for (int i = 0; i < alength; i++) {
            newint[i] = array1[i];
        }
        int index = alength;
        //有相同项为true，没有为false
        boolean flag = false;
        for (int c = 0; c < blength; c++) {
            for (int j = 0; j < alength; j++) {
                if (array1[j] == array2[c]) {

                    flag = true;
                    break;
                }

            }
            if (flag) {

                flag = false;
            } else {
                newint[index] = array2[c];
                index++;
            }

        }
        // 去零
        newint = removeZero(newint);
        //排序

        quickSort(newint);
        return newint;
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
        int[] newarry = new int[oldArray.length + size];
        for (int i = 0; i < oldArray.length; i++) {
            newarry[i] = oldArray[i];
        }
        return newarry;
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
        int count = 0;
        for (int i = 0; ; i++) {
            if (createfibonacci(i + 1) < max) {
                count++;
            } else {
                break;

            }
        }
        int[] arry = new int[count];
        for (int a = 0; a < count; a++) {
            arry[a] = createfibonacci(a + 1);
        }
        return arry;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int count = 0;
        for (int i = 0; i < max; i++) {
            if (isprime(i)) {
                count++;
            }
        }
        int[] arry = new int[count];
        int sign = 0;
        for (int i = 0; i < max; i++) {
            if (isprime(i)) {
                arry[sign] = i;
                sign++;
            }
        }
        return arry;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        int count = 0;
        for (int i = 0; i < max; i++) {
            if (isperfectnmber(i)) {
                count++;
            }
        }
        int[] arry = new int[count];
        int sign = 0;
        for (int i = 0; i < max; i++) {
            if (isperfectnmber(i)) {
                arry[sign] = i;
                sign++;
            }
        }
        return arry;

    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param
     * @return
     */
    public String join(int[] array, String seperator) {
        String stringBuilder=new String(String.valueOf(array[0]));
        for (int i = 1; i <array.length ; i++) {
                stringBuilder = stringBuilder + seperator + new String(String.valueOf(array[i]));

        }
        return stringBuilder;
    }

    //快排
    public static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private static void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot - 1);                   //递归排序左子数组
            qsort(arr, pivot + 1, high);                  //递归排序右子数组
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];     //枢轴记录
        while (low < high) {
            while (low < high && arr[high] >= pivot) --high;
            arr[low] = arr[high];             //交换比枢轴小的记录到左端
            while (low < high && arr[low] <= pivot) ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }

    //生成斐波那契数列
    public static int createfibonacci(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return createfibonacci(n - 1) + createfibonacci(n - 2);
        }
    }

    //判断是否是素数
    public static boolean isprime(int a) {
        boolean flag = true;
        if (a < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    //判断是否是完数
    public static boolean isperfectnmber(int a) {
        boolean flag = true;
        int temp = 0;// 定义因子之和变量

        for (int n = 1; n < a / 2 + 1; n++) {
            if (a % n == 0) {
                temp += n;// 能被整除的除数则被加到temp中
            }
        }
        if (temp == a) {// 如果因子之和与原数相等的话，说明是完数
            //System.out.print(a + " ");// 输出完数
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

}
