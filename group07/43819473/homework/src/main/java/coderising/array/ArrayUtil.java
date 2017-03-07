package coderising.array;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public static int[] reverseArray(int[] origin) {
        int[] result = new int[origin.length];
        for (int i = 0; i <= origin.length - 1; i++) {
            result[i] = origin[origin.length - 1 - i];
        }
        return result;
    }

    /**
     * 现在有如下的一个数组：   int origin[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     *
     * @param origin
     * @return
     */

    public static int[] removeZero(int[] origin) {
        int[] tempArray = new int[origin.length];
        int j = 0;
        for (int i = 0; i <= origin.length - 1; i++) {
            if (origin[i] != 0) {
                tempArray[j++] = origin[i];
            }
        }

        int[] result = new int[j];
        System.arraycopy(tempArray, 0, result, 0, j);
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

    public static int[] merge(int[] array1, int[] array2) {
        if (array1.length == 0) {
            return array2;
        }
        if (array2.length == 0) {
            return array1;
        }
        int[] tempArray = new int[array1.length + array2.length];
        int currentSize = array1.length;
        System.arraycopy(array1, 0, tempArray, 0, array1.length);
        for (int i = 0; i <= array2.length - 1; i++) {
            for (int j = 0; j <= currentSize - 1; j++) {
                if (array2[i] == tempArray[j]) {
                    break;
                } else if (array2[i] < tempArray[j]) {
                    System.arraycopy(tempArray, j, tempArray, j + 1, currentSize - j);
                    tempArray[j] = array2[i];
                    currentSize++;
                    break;
                }
                if (j == currentSize - 1) {
                    tempArray[j + 1] = array2[i];
                    currentSize++;
                    break;
                }
            }
        }

        int[] result = new int[currentSize];
        System.arraycopy(tempArray, 0, result, 0, currentSize);
        return result;
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
    public static int[] grow(int[] oldArray, int size) {
        int[] result = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, result, 0, oldArray.length);
        return result;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public static int[] fibonacci(int max) {
        if (max <= 1) {
            return new int[]{};
        }

        int[] temp = new int[max];
        temp[0] = 1;
        temp[1] = 1;

        int i = 2;
        while (i >= 2) {
            int last = temp[i - 1] + temp[i - 2];
            if (last < max) {
                temp[i++] = last;
            } else {
                break;
            }
        }

        int[] result = new int[i];
        System.arraycopy(temp, 0, result, 0, i);
        return result;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        } else if (max == 3) {
            return new int[]{2};
        } else {
            int[] temp = new int[max / 2 + 1];
            temp[0] = 2;
            int resultSize = 1;

            for (int i = 3; i < max; i += 2) {
                boolean isPrime = true;
                for (int j = 0; j <= resultSize - 1; j++) {
                    if (i % temp[j] == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    temp[resultSize++] = i;
                }
            }
            int[] result = new int[resultSize];
            System.arraycopy(temp, 0, result, 0, resultSize);

            return result;
        }
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        int[] tempResult = new int[max];
        int resultSize = 0;

        //对小于max的值逐个循环判断
        for (int i = 2; i < max; i++) {
//            System.out.println("==="+i);
            int[] factors = new int[max];//因子数组
            factors[0] = 1;
            int factorSize = 1;

            //获取因子
            for (int j = 2; j < i / 2 + 1; j++) {
                if (i % j == 0) {
                    factors[factorSize++] = j;
                }
            }

            //计算因子数组的和是否与当前值相等，相等则放入结果数组中
            if (factorSize > 1) {
                int sumValue = 0;
                for (int factorIndex = 0; factorIndex <= factorSize - 1; factorIndex++) {
                    sumValue += factors[factorIndex];
                }
                if (sumValue == i) {
                    tempResult[resultSize++] = i;
                }
            }
        }

        int[] result = new int[resultSize];
        System.arraycopy(tempResult, 0, result, 0, resultSize);
        return result;
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
    public static String join(int[] array, String seperator) {
        int size = array.length;
        String result = "";
        if (size > 0) {
            if (size == 1) {
                result = String.valueOf(array[0]);
            } else {
                for (int i = 0; i < array.length - 1; i++) {
                    result += String.valueOf(array[i]) + seperator;
                }
                result += String.valueOf(array[array.length - 1]);
            }
        }

        return result;
    }

}
