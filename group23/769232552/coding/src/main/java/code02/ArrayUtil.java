package code02;
import org.apache.commons.lang.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if (origin == null || origin.length <= 1){
			return;
		}

		int head = 0;
		int tail = origin.length - 1;
		int tmp;
		while (head != tail){
			//调换位置
			tmp = origin[head];
			origin[head] = origin[tail];
			origin[tail] = tmp;

            head ++;
            tail --;
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
		if (oldArray == null || oldArray.length < 1){
			return null;
		}

		List<Integer> newList = new ArrayList<Integer>();
		for(int number : oldArray){
			if(number != 0){
				newList.add(number);
			}
		}

        Integer[] result = new Integer[newList.size()];
        result = (Integer[]) newList.toArray(result);
        return ArrayUtils.toPrimitive(result);


	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
        if(array1 == null && array2 == null){
            return null;
        }
        if(array1 == null){
            return array2;
        }
        if(array2 == null){
            return array1;
        }
        int[] newArray = new int[array1.length + array2.length];
        int m = 0,n = 0, k = 0;
        while (m < array1.length && n < array2.length){
            if(array1[m] <= array2[n]){
                newArray[k++] = array1[m++];
            }else {
                newArray[k++] = array2[n++];
            }
        }
        if(m >= array1.length){
            while (n < array2.length){
                newArray[k++] = array2[n++];
            }
        }
        if(n >= array2.length){
            while (m < array1.length){
                newArray[k++] = array1[m++];
            }
        }
		return  newArray;
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
        int i = 0;
        for (; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        for (int j = 0; j < size; j++){
            newArray[i++] = 0;
        }
        return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
    //也就是需要生成一个小于max值的fibonacci数组
	public int[] fibonacci(int max){
        if(max < 2){
            return new int[]{};
        }
        if(max < 3){
            return new int[]{1,1};
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(0,1);
        list.add(1,1);
        int i=0;
        while (list.get(i) + list.get(i+1) < max){
            list.add(i+2,list.get(i) + list.get(i+1));
            i++;
        }

        int[] newArray = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            newArray[j] = list.get(j).intValue();
        }
        return newArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
     *
     * 原理：
     * １，判断一个数字是否为素数，一个数 n 如果是合数，那么它的所有的因子不超过sqrt(n)
     * ２，当i是素数的时候，i的所有的倍数必然是合数。
	 */
	public int[] getPrimes(int max){

        if(max <= 2){
            return null;
        }
        boolean[] prime = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            if(i%2 == 0){
                prime[i] = false; //偶数
            }else {
                prime[i] = true;
            }
        }

        for (int i = 2; i <= Math.sqrt(max) ; i++) {
            if(prime[i]){//奇数
                //如果i是素数，那么把i的倍数标记为非素数
                for(int j = i+i; j <= max; j += i){
                    prime[j] = false;
                }
            }
        }

        List num = new ArrayList<Integer>();
        for (int i = 2; i <= max; i++) {
            if(prime[i]){
                num.add(i);
            }
        }

        Integer[] result = new Integer[num.size()];
        result = (Integer[]) num.toArray(result);
        return ArrayUtils.toPrimitive(result);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){

        if(max < 6){
            return null;
        }

        List<Integer> perfectNumlist = new ArrayList<Integer>();

        for (int j = 6;j <= max; j++){
            List<Integer> factorNumlist = new ArrayList<Integer>();
            factorNumlist.add(1);
            for (int i = 2; i < j; i++) {
                if(j % i == 0){
                    factorNumlist.add(i);
                }
            }
            int sum = 0;
            for(Integer num : factorNumlist){
                sum += num;
            }

            if(sum == j){
                perfectNumlist.add(j);
            }
        }
        Integer[] result = new Integer[perfectNumlist.size()];
        result = (Integer[]) perfectNumlist.toArray(result);
        return ArrayUtils.toPrimitive(result);
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]);
            sb.append(seperator);
        }
        sb.append(array[array.length - 1]);
        return sb.toString();
	}

    public void printArr(int[] array){
        for(int num : array){
            System.out.print(num + " ");
        }
    }

}
