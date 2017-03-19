public class ArrayUtil {

    /**
    * 给定一个整形数组a , 对该数组的值进行置换
    例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
    如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
    * @param origin
    * @return
    */
    public void reverseArray(int[] origin){
        int lo = 0;
        int hi = origin.length - 1;
        while (lo < hi)
            swap(origin, lo++, hi--);
    }
    private void swap(int[] array, int lo, int hi) {
        int temp = array[lo];
        array[lo] = array[hi];
        array[hi] = temp;
    }

    /**
    * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
    * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
    * {1,3,4,5,6,6,5,4,7,6,7,5}  
    * @param oldArray
    * @return
    */

    public int[] removeZero(int[] oldArray){
        int[] ret = new int[oldArray.length];
        int i = 0;
        for (int j = 0; j < oldArray.length; j++) {
            if (oldArray[j] != 0) 
            ret[i++] = oldArray[j];
        }
        int[] old = ret;
        ret = new int[i];
        for (int j = 0; j < i; j++)
            ret[j] = old[j];

        return ret;
    }

    /**
    * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
    * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
    * @param array1
    * @param array2
    * @return
    */

    public int[] merge(int[] array1, int[] array2){
        int m = array1.length;  //array1 —— m —— i
        int n = array2.length;  //array2 —— n —— j
        int[] ret = new int[m + n]; // ret —— m+n —— k
        int k = 0;
        for (int i = 0, j = 0; i < m || j < n; ) {
            if (i < m && (n <= j || array1[i] < array2[j])) ret[k++] = array1[i++];
            if (j < n && (m <= i || array2[j] < array1[i])) ret[k++] = array2[j++];
            if (i < m && j < n && array1[i] == array2[j]) {
                ret[k++] = array1[i++];
                j++;
            }
        }
        int[] old = ret;
        ret = new int[k];
        for (int i = 0; i < k; i++)
            ret[i] = old[i];

        return ret;
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
        int[] ret = new int[oldArray.length + size];

        for (int i = 0; i < oldArray.length; i++)
            ret[i] = oldArray[i];        

        return ret;
    }

    /**
    * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
    * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
    * max = 1, 则返回空数组 []
    * @param max
    * @return
    */
    public int[] fibonacci(int max){
        int[] ret = new int[max / 2 + 10];
        int f = 1, g = 0, i = 0;
        for ( ; f < max; i++) {
            ret[i] = f;
            f = g + f;
            g = f - g;
        }
        int[] old = ret;
        ret = new int[i];
        for (int j = 0; j < i; j++)
            ret[j] = old[j];
        return ret;
    }

    /**
    * 返回小于给定最大值max的所有素数数组
    * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
    * @param max
    * @return
    */
    public int[] getPrimes(int max){
        int[] ret = new int[max / 3 + 10]; //节省初始化开辟的空间；ret —— i
        int i = 0;                //i用来向ret中添加素数
        //退化情况: max < 5的情况
        if (2 < max) { ret[i++] = 2; }                    
        if (3 < max) { ret[i++] = 3; }
        if (5 < max) { ret[i++] = 5; }
        if (7 < max) { 
            //素数只能为6k+1、6k+5的类型
            //k的最小值：1
            //判断k的最大值：6k + 1 <= max其中6k + 5与max的比较需要自己确定            
            int k = 1;
            while (6 * k + 1 <= max) {
                int m = 6 * k + 1;
                int n = 6 * k + 5;
                if(isPrime(ret, m))  ret[i++] = m;
                if (max < n) break;
                if (isPrime(ret, n)) ret[i++] = n;
                k++;
            }            
        }//O(n/3) * O((n^0.5) / 3)
        int[] old = ret;
        ret = new int[i];
        for (int j = 0; j < i; j++)
            ret[j] = old[j];
        return ret;
        }

        private boolean isPrime(int[] primeArray, int target) {
        //O((n^0.5) / 3)
            boolean isPrime = true;
            int min = (int)Math.sqrt(target);
            for (int i = 0; primeArray[i] <= min; i++) {
            if (target % primeArray[i] == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;       
    }

    /**
    * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
    * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
    * @param max
    * @return
    */
    public int[] getPerfectNumbers(int max){
        int[] ret = new int[48];
        int[] supportArr = getPrimes(max);
        int j = 0;
        for (int i = 2; i < max; i++) {
            if (i % 2 != 0) continue;
            if (isPerfectNumber(i, supportArr)) ret[j++] = i;
        }
        int[] old = ret;
        ret = new int[j];
        for (int i = 0; i < j; i++)
            ret[i] = old[i];
        return ret;
    }
    private boolean isPerfectNumber(int target, int[] supportArr) {
        //套用公式：perfectNum = ( 2^p-1 ) * 2^(p-1) = ( 2^(count+1)-1 ) * ( 2^count )
        //其中p=count+1是质数、2^p-1=2^(count+1)-1也是质数
        //count: 完数中因子2的个数    
        boolean isPerfectNum = true;
        int count = amountOfTwo(target);            

        int test0 = (int)Math.pow(2, count);
        int test1 = count + 1;
        int test2 = test0 * 2 - 1;

        if (count == 0) isPerfectNum = false;
        else if (!isPrime(supportArr, test1)) isPerfectNum = false;
        else if (!isPrime(supportArr, test2)) isPerfectNum = false;
        else if (test0 * test2 != target) isPerfectNum = false;

        return isPerfectNum;
    }
    private int amountOfTwo(int num) {
        int count = 0;
        while (num % 2 == 0) {
            num /= 2;
            count++;
        }
        return count;
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
        String ret = "";
        if (array.length < 1) return ret;
        ret += array[0];
        for (int i = 1; i < array.length; i++)
            ret += seperator + array[i];
        return ret;
    }

    public static void main(String[] args) {
        ArrayUtil au = new ArrayUtil();

        int[] arr0 = au.fibonacci(50000000);
        for (int i = 0; i < arr0.length; i++)
        System.out.print(arr0[i] + " ");
        //      arr1 = {3,};
        //System.out.println(au.join(arr0, "-"));
        //System.out.println(au.join(arr1, "-"));

    }

}


































