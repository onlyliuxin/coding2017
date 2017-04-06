package array;

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
        int temp;
        for (int i = 0; i < origin.length / 2; i++) {
            temp = origin[i];
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
        int count = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] == 0) {
                count++;
            }
        }
        int[] newArray = new int[oldArray.length - count];
        int flag = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                newArray[flag] = oldArray[i];
                flag++;
            } else {
                continue;
            }
        }
        return newArray;
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

        /*
        int[] temp = new int[array1.length + array2.length];
        int count = array1.length;
        int point = array1.length;
        for (int i = 0; i < array1.length; i++) {
            temp[i] = array1[i];
        }
        boolean flag = true;
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                if (array1[j] == array2[i]) {
                    flag = false;
                }
            }
            if (flag) {
                temp[count]=array2[i];
                count++;
            }
            flag = true;
        }
        */
        if (array1.length == 0) {
            return array2;
        }
        if (array2.length == 0) {
            return array1;
        }

        int[] temp = new int[array1.length + array2.length];
        int ap = 0;
        int bp = 0;
        int count = 0;
        while (ap < array1.length && bp < array2.length) {
            if (array1[ap] == array2[bp]) {
                temp[count] = array1[ap];
                ap++;
                bp++;
                count++;
            } else if (array1[ap] > array2[bp]) {
                temp[count] = array2[bp];
                bp++;
                count++;
            } else {
                temp[count] = array1[ap];
                ap++;
                count++;
            }
        }
        if (ap == array1.length) {
            for (int i = bp; i < array2.length; i++) {
                temp[count] = array2[i];
                count++;
            }
        } else if (bp == array2.length) {
            for (int i = ap; i < array1.length; i++) {
                temp[count] = array1[i];
                count++;
            }


        }
        int array3[] = new int[count];
        System.arraycopy(temp, 0, array3, 0, count);

        return array3;
        /*int[] temp = new int[array2.length];
        boolean flag = true;
        int count = 0;
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                if (array2[j] == array1[i]) {
                    flag = false;
                }
            }
            if (flag) {
                temp[count] = array2[i];
                count++;
            }
        }
        if (count == 0) {
            return array1;
        }

        int ap = 0;                                     //数组1的指针
        int bp = 0;                                     //数组2的指针
        int[] array3 = new int[count + array1.length];
        for (int i = 0; i < array3.length; i++) {
            if (array1[ap] > array2[bp]) {
                array3[i] = array2[bp];
                bp++;
            }else {
                array3[i] = array1[ap];
                ap++;
            }

        }
*/

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
        int[] newArray = new int[oldArray.length + size];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
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

        if (max == 1) {
            return null;
        }
        int a = 1;
        int b = 1;
        int[] result = {1, 1};
        int[] temp;
        while (b < max) {

            b = a + b;
            a = b - a;
            temp = result;
            result = new int[result.length + 1];

            for (int i = 0; i < temp.length; i++) {
                result[i] = temp[i];
            }
            result[result.length - 1] = b;
        }
        temp = result;
        result = new int[result.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
       /* int[] result = null;
        int[] temp = null;
        if (max < 2) {
            return null;
        }
        boolean flag = true;
        for (int i = 2; i < max; i++) {
            for (int j = 2; j * j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                }
            }
            if (flag) {
                if (result == null) {
                    result = new int[1];
                    result[0] = i;
                } else {
                    temp = result;
                    result = new int[result.length + 1];
                    for (int j = 0; j < temp.length; j++) {
                        result[j] = temp[j];
                    }
                    result[result.length - 1] = i;
                }
            }
            flag = true;
        }

        return result;*/
        if (max < 2) {
            return null;
        }
        int[] result = {2};
        int[] temp ;
        boolean flag = true;
        for (int i = 3; i < max; i++) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0){
                    flag = false;
                }
            }
            if (flag) {
                temp=result;
                result=new int[temp.length+1];
                System.arraycopy(temp,0,result,0,temp.length);
                result[result.length-1]=i;
            }
            flag=true;

        }
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
        int[] result = {};
        int[] temp = null;
        int count = 0;
        for (int i = 1; i < max; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    count += j;
                }
            }
            if (count == i) {
                temp = result;
                result = new int[temp.length + 1];
                for (int j = 0; j < temp.length; j++) {
                    result[j] = temp[j];
                }
                result[result.length - 1] = i;
            }

        }
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
    public String join(int[] array, String seperator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]);
            sb.append(seperator);
        }
        sb.append(array[array.length - 1]);
        return sb.toString();

    }


}
