package me.lzb.basic;

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
        int[] target = new int[origin.length];
        for (int i = 0; i < origin.length; i++) {
            target[i] = origin[origin.length - 1 - i];
        }
        origin = target;
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
        int l = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                l = l + 1;
            }
        }

        int[] target = new int[l];

        int a = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                target[a] = oldArray[i];
                a = a + 1;
            }
        }

        return target;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     *
     * @param array1
     * @param array2
     * @return
     */

    //一个一个放进去，循环次数有点多
    public int[] merge(int[] array1, int[] array2) {


        int[] tmp = new int[array1.length + array2.length];


        int mini = 0;
        int a1 = array1[0];
        int a2 = array2[0];

        if(a1 < a2){
            mini = a1;
        }else {
            mini = a2;
        }

        tmp[0] = mini;

        int l3 = 0;

        for (int i = 1; i < array1.length + array2.length; i++) {

//            if(mini >= array1[l1 - 1] && mini >= array2[l2 - 1]){
//                l3 = i;
//                break;
//            }

            int oldMin = mini;



            int aa1 = mini;
            if(mini < array1[array1.length - 1] ){
                for (int j = 0; j < array1.length; j++) {
                    if(array1[j] > mini){
                        aa1 = array1[j];
                        break;
                    }
                }

            }

            int aa2 = mini;
            if(mini < array2[array2.length - 1] ){
                for (int j = 0; j < array2.length; j++) {
                    if(array2[j] > mini){
                        aa2 = array2[j];
                        break;
                    }
                }
            }


            if(aa1 != oldMin && aa2 != oldMin){
                if(aa1 < aa2){
                    mini = aa1;
                }else {
                    mini = aa2;
                }
            }else if(aa1 != oldMin){
                mini = aa1;
            }else {
                mini = aa2;
            }


            if(oldMin == mini){
                l3 = i;
                break;
            }

            tmp[i] = mini;
        }

        int[] result = new int[l3];

        System.arraycopy(tmp, 0, result, 0, l3);



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
    public int[] grow(int[] oldArray, int size) {
        int newArray[] = new int[oldArray.length + size];

        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
        if (max <= 1){
            return new int[0];
        }

        int[] result = {1, 1};


        int i = 2;

        int n = 0;

        while (n < max){
            int[] t = new int[result.length + 1];
            System.arraycopy(result, 0, t, 0, result.length);
            n = t[i-1] + t[i - 2];

            if(n >= max){
                return result;
            }

            t[i] = n;

            result = t;
            i = i + 1;
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
        if (max <= 2){
            return new int[0];
        }

        if (max == 3){
            return new int[]{2};
        }


        int[] primes = new int[max+1];
        primes[0] = 2;
        int count = 1;
        for (int i = 3; i < max; i = i + 2) {

            boolean isPrime = true;
            for (int j = 3; j < i; j++) {
                if(i % j == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime){
                primes[count] = i;
                count = count + 1;
            }
        }

        int[] result = new int[count];
        System.arraycopy(primes, 0, result, 0, count);

        return result;

    }

    private boolean isPrime(int a){
        if (a < 2) {
            return false;
        }

        if (a == 2) {
            return true;
        }

        if(a % 2 == 0){
            return false;
        }


        for (int i = 3; i < a; i = i + 2) {
            if(a % i == 0){
                return false;
            }
        }

        return true;
    }



    /**
     * 所谓“完数”， 是指这个数恰好等于它的真因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        if (max < 6){
            return new int[0];
        }


        int[] pns = new int[max];

        int count = 0;
        for (int i = 6; i < max; i++) {
            if (isPerfectNumber(i)){
                pns[count] = i;
                count = count + 1;
            }
        }



        int[] result = new int[count];
        System.arraycopy(pns, 0, result, 0, count);
        return result;
    }


    private boolean isPerfectNumber(int a){
        if(a < 6){
            return false;
        }

        int sum = 0;
        for (int i = 1; i < a; i++) {
            if(a % i == 0){
                sum = sum + i;
            }
        }

        return sum == a;
    }



    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @return
     */
    public static  String join(int[] array, String seperator) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result = result + array[i] + seperator ;
        }

        result = result.substring(0, result.length() - 1);
        return result;
    }

}
