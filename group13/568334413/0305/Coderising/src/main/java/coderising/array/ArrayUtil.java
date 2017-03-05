package coderising.array;

public class ArrayUtil {


    private void checkArray(int[] origin) {
        if (origin == null) {
            throw new NullPointerException("array  is null refresh ");
        }
    }

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * 4 9 30 3 7
     * 4 3 30 9 7
     *
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        if (origin.length < 2) {
            return;
        }
        for (int i = 0; i < origin.length / 2; i++) {
            int temp = origin[i];
            origin[i] = origin[origin.length - i - 1];
            origin[origin.length - i - 1] = temp;
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
        checkArray(oldArray);

        int[] newArr = new int[oldArray.length];
        int index = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                newArr[index] = oldArray[i];
                index++;
            }
        }
        int[] newArrs = new int[index];
        System.arraycopy(newArr, 0, newArrs, 0, index);
        return newArrs;
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
        if (array1 == null && array2 == null) {
            return new int[0];
        } else if (array1 == null || array1.length < 1) {
            return array2;
        } else if (array2 == null || array2.length < 1) {
            return array1;
        }
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        int[] tempArr = new int[array1.length + array2.length];
        for (int i = index1; i < tempArr.length; i++) {
            for (int j = index2; j < array2.length; j++) {
                int i1 = array1[index1];
                int i2 = array2[index2];

                if (i1 > i2) {
                    tempArr[index] = i2;
                    index2 = index2 + 1;
                    index++;
                    break;
                } else if (i1 < i2) {
                    tempArr[index] = i1;
                    index1 = index1 + 1;
                    index++;
                    break;
                } else {
                    tempArr[index] = i1;
                    index1 = index1 + 1;
                    index2 = index2 + 1;
                    index++;
                    break;
                }
            }
            if (index2 >= array2.length && index1 < array1.length) {
                tempArr[index] = array1[index1];
                index1 = index1 + 1;
                index++;
            }
        }
        int[] newArr = new int[index];
        System.arraycopy(tempArr, 0, newArr, 0, index);
        return newArr;
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
        int[] newArr = new int[oldArray.length + size];
        for (int i = 0; i < oldArray.length; i++) {
            newArr[i] = oldArray[i];
        }
        return newArr;
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
        int[] tempArray = new int[max];
        int[] ints = new int[0];
        for (int i = 1; i <= max; i++) {
            int num = fibonacci1(i);
            if (num < max) {
                tempArray[i - 1] = num;
            } else {
                ints = new int[i - 1];
                System.arraycopy(tempArray, 0, ints, 0, i - 1);

                break;
            }
        }


        return ints;
    }

    // 递归实现方式
    public static int fibonacci1(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibonacci1(n - 1) + fibonacci1(n - 2);
        }
    }


    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int i, j, index = 0;
        int[] tempArray = new int[max];

        for (i = 1; i <= max; i++) {
            for (j = 2; j < i; j++)
                if (i % j == 0) break;
            if (j < i)
                continue;
            else {
                tempArray[index] = i;
                index++;
                System.out.println(i);
            }
        }
        int[] arr = new int[index];
        System.arraycopy(tempArray, 0, arr, 0, index);

        return arr;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        int sum = 0, i, j, index = 0;
        int[] tempArray = new int[max];
        for (i = 1; i <= max; i++) {
            for (j = 1, sum = 0; j <= i / 2; j++) {
                if (i % j == 0)
                    sum += j;
            }
            if (sum == i) {
                System.out.println("完数：" + i);
                tempArray[index] = i;
                index++;
            }
        }
        int[] arr = new int[index];
        System.arraycopy(tempArray, 0, arr, 0, index);
        return arr;
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
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(i);
            if (i != array.length - 1) {
                stringBuilder.append(seperator);
            }
        }
        System.out.println("stringBuilder = " + stringBuilder.toString());

        return stringBuilder.toString();
    }


}
