package week2.com.coding.basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrayUtil
{
    
    /**
     * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * 
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin)
    {
        int[] tempArrays = new int[origin.length];
        int j = 0;
        for (int i = origin.length - 1; i > -1; i--)
        {
            tempArrays[j] = origin[i];
            j++;
        }
    }
    
    /**
     * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * 
     * @param oldArray
     * @return
     */
    
    public int[] removeZero(int[] oldArray)
    {
        // ArrayList不需要知道长度，所以用在此处最方便
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < oldArray.length; i++)
        {
            if (oldArray[i] != 0)
            {
                list.add(oldArray[i]);
            }
        }
        int[] newArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            newArray[i] = list.get(i);
        }
        return newArray;
        
    }
    
    /**
     * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 = [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3
     * 为[3,4,5,6,7,8] , 注意： 已经消除了重复
     * 
     * @param array1
     * @param array2
     * @return
     */
    
    public int[] merge(int[] array1, int[] array2)
    {
        //Set集合不允许重复值
        Set<Integer> set = new HashSet<Integer>();
        for (int i : array1)
        {
            set.add(i);
        }
        for (int i : array2)
        {
            set.add(i);
        }
        int[] newArr = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        int i = 0;
        while (it.hasNext())
        {
            newArr[i] = it.next();
            i++;
        }
        // 对newArr冒泡排序
        for (int j = 0; j < newArr.length - 1; j++)
        {
            for (int k = 0; k < newArr.length - 1 - j; k++)
            {
                int temp = 0;
                if (newArr[k] > newArr[k + 1])
                {
                    temp = newArr[k];
                    newArr[k + 1] = temp;
                }
            }
        }
        return newArr;
    }
    
    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size =
     * 3,则返回的新数组为 [2,3,6,0,0,0]
     * 
     * @param oldArray
     * @param size
     * @return
     */
    public  int[] grow(int[] oldArray, int size)
    {
        int[] newArray = Arrays.copyOf(oldArray, oldArray.length + size);
        return newArray;
    }
    
    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
     * 
     * @param max
     * @return
     */
    public  int[] fibonacci(int max)
    {
        // f(n)=f(n-1)+f(n-2)
        // f(0)=1 f(1)=1 f(2)=f(0)+f(1)=2
        List<Integer> list = new ArrayList<Integer>();
        int[] newArr = null;
        if (max == 1)
            return newArr;
        int num = 0;
        int x = 1, y = 1;
        list.add(1);// f(0)=1;
        list.add(1);// f(1)=1;
        while (true)
        {
            num = x + y;
            x = y;
            y = num;
            if (num >= max)
                break;
            list.add(num);
        }
        newArr = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
        {
            newArr[k] = list.get(k);
        }
        return newArr;
    }
    
    /**
     * 斐波那契数列的递归算法
     * 
     * @param i
     * @return
     */
    public static int getFiboo(int i)
    {
        if (i == 1 || i == 2)
            return 1;
        else
            return getFiboo(i - 1) + getFiboo(i - 2);
    }
    
    /**
     * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * 
     * @param max
     * @return
     */
    public int[] getPrimes(int max)
    {
        int[] newArr = null;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < max; i++)
        {
            boolean flag = isPrime(i);
            if (flag)
                list.add(i);
        }
        newArr = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
        {
            newArr[k] = list.get(k);
        }
        return newArr;
    }
    
    public static boolean isPrime(int n)
    {
        if (n == 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;// 偶数肯定不是质数
        for (int i = 3; i < n; i += 2)// 去掉偶数的判断
        {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    
    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * 
     * @param max
     * @return
     */
    public  int[] getPerfectNumbers(int max)
    {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> factorList = null;
        for (int i = 1; i < max; i++)
        {
            // 所有的素数不是完数
            if (isPrime(i))
            {
                continue;
            }
            factorList = getFactor(i);
            int count = 0;
            for (int j = 0; j < factorList.size(); j++)
            {
                count += factorList.get(j);
            }
            if (count != i)
                continue;
            list.add(i);
        }
        int[] newArr = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
        {
            newArr[k] = list.get(k);
        }
        return newArr;
    }
    
    /**
     * 求一个数的所有因子
     **/
    public static List<Integer> getFactor(int number)
    {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < number; i++)
        {
            if (number % i != 0)
                continue;
            list.add(i);
        }
        return list;
    }
    
    /**
     * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
     * 
     * @param array
     * @param s
     * @return
     */
    public  String join(int[] array, String seperator)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++)
        {
            if (i == array.length - 1)
            {
                sb.append(array[i]);
                break;
            }
            sb.append(array[i] + seperator);
        }
        return sb.toString();
    }
    
}