package basic.dataStructure;

/**
 * @author : 温友朝
 * @date : 2017/4/5
 */
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
        int[] reversed = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            reversed[length - i - 1] = origin[i];
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
        int length = oldArray.length;
        int[] arr = new int[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (oldArray[i] != 0) {
                arr[index] = oldArray[i];
                index++;
            }
        }
        //非0的数据个数
        int[] newArr = new int[index];
        System.arraycopy(arr, 0, newArr, 0, index);
        return newArr;
    }

    public static Object[] remove(Object[] oldArray, Object value){
        int length = oldArray.length;
        Object[] arr = new Object[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (oldArray[i] != value) {
                arr[index] = oldArray[i];
                index++;
            }
        }
        Object[] newArr = new Object[index];
        System.arraycopy(arr, 0, newArr, 0, index);
        return newArr;
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
        int length1 = array1.length;
        int length2 = array2.length;
        int[] arr = new int[length1 + length2];

        System.arraycopy(array1, 0, arr, 0, length1);
        System.arraycopy(array2, 0, arr, length1, length2);

        //去重
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(i != j && arr[i] == arr[j]){
                    arr[j] = 0;
                }
            }
        }


        int[] data = removeZero(arr);
        int length = data.length;

        //排序
        for (int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++){
                if(data[i] < data[j]){
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
        return data;
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
        int[] arr = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, arr, 0, oldArray.length);
        return arr;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *  F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {
        int[] empty = {};
        int[] arr2 = {1, 1};

        switch (max){
            case 0 : return empty;
            case 1 : return empty;
            case 2 : return arr2;
            default: {
                int[] data = arr2;
                int d = data[0] + data[1];
                while (d < max){
                    int length = data.length;
                    d = data[length - 1] + data[length - 2];
                    if(d > max){
                        return data;
                    }
                    int[] temp = new int[data.length + 1];
                    System.arraycopy(data, 0, temp, 0, length);
                    temp[length] = d;

                    data = temp;
                }
            }
        }

        return null;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int[] data = new int[max];
        int index = 0;
        for(int i = 1; i < max; i++){
            int divided = 0;
            for(int j = i; j >= 1; j--){
                if(i % j == 0){
                    divided++;
                }
                if(divided > 2){
                    break;
                }else if(j == 1 && divided == 2){
                    data[index] = i;
                    index ++;
                }
            }
        }

        int[] result = new int[index];
        System.arraycopy(data, 0, result, 0, index);
        return result;

    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        int[] perfd = new int[max];
        int perfIndex = 0;
        for(int i = 1; i <= max; i++){
            int index = 0;
            int[] data = new int[i];
            for(int j = i - 1; j >= 1; j--){
                if(i % j == 0){
                    data[index] = j;
                    index ++;
                }

                if(j == 1 && getSum(data) == i){
                    perfd[perfIndex] = i;
                    perfIndex++;
                }
            }
        }

        return removeZero(perfd);
    }

    private int getSum(int[] arr){
        int sum = 0;
        for(int i : arr){
            sum += i;
        }
        return sum;
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
        StringBuffer sb = new StringBuffer();
        for(int i : array){
            sb.append(i).append(seperator);
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
