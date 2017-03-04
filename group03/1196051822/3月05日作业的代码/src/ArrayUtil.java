import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int tmp ;
		int length = origin.length;
		for (int i = 0 ;  i < length / 2 ; i++) {
			tmp = origin[i];
            origin[i] = origin[length - i - 1];
            origin[length - i - 1] = tmp;
        }
	}

	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
	 * {1,3,4,5,6,6,5,4,7,6,7,5}
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray){
        int size = oldArray.length;
        int[] newArray = new int[size];
        int repeatTime = 0;
        int count = 0;
        for (int i = 0 ; i < oldArray.length;i++) {
            if (oldArray[i] != 0) {
                newArray[count++] = oldArray[i];
            }else{
                repeatTime++;
            }
        }
        return Arrays.copyOf(newArray,newArray.length - repeatTime);
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2){
        int[] newArray = new int[array1.length + array2.length];
        int count = 0;
        int cursor = 0;
        int last = 0;
        int repeatTime = 0;
        for (int i = 0 ; i < array1.length;i++) {
            last = i;
            int value1 = array1[i];
            if (value1 < array2[cursor]) {
                newArray[count++] = value1;
            }else{
                newArray[count++] = array2[cursor];
                if (value1 != array2[cursor]) {
                    i--;
                }else{
                    repeatTime++;
                }
                cursor++;
                if (cursor == array2.length) {
                    break;
                }
            }
        }
        for (int i = cursor ; i < array2.length ;i++) {
            if (newArray[count - 1] == array2[i]) {
                continue;
            }
            newArray[count++] = array2[i];
        }

        for (int i = last;i < array1.length;i++) {
            if (newArray[count - 1] == array1[i]) {
                continue;
            }
            newArray[count++] = array1[i];
        }
        return Arrays.copyOf(newArray,newArray.length - repeatTime);
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
        int[] newArray = new int[oldArray.length + size];
        System.arraycopy(oldArray,0,newArray,0,oldArray.length);
        return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
        if (max == 1) {
            return new int[]{};
        }
        int num1 = 1;
        int num2 = 1;
        int next = 2;
        int [] array = new int[max + 1];
        array[0] = num1;
        int count = 1;
        while (next <= max) {
            array[count++] = num2;
            next = num1 + num2;
            num1 = num2;
            num2 = next;
        }
        return Arrays.copyOf(array,count);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
        int[] primes = new int[max];
        int count = 0;
        boolean isPrime = true;
        for (int i = 2 ; i < max ;i ++) {
            for (int j = 2 ; j < Math.sqrt(i);j++) {
                if (i % j == 0){
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primes[count++] = i;
            }

            isPrime = true;
        }
        return Arrays.copyOf(primes,count);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
        int[] array = new int[max];
        int count = 0;
        for (int i = 1 ; i < max ; i++) {
            int sum = 0;
            for (int j = 1 ; j < i;j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                array[count++] = i;
            }
        }
        return Arrays.copyOf(array, count);
    }

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public String join(int[] array, String seperator){
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < array.length;i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(seperator);
            }
        }
        return sb.toString();
	}


}
