/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

import java.util.Arrays;

/**
 *
 * @author CJ
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
     * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        int totalLen = origin.length;
        int[] revIntArr = new int[totalLen];
        for (int i = 0; i < totalLen; i++) {
            revIntArr[i] = origin[totalLen - 1 - i];
        }
        for (int i = 0; i < totalLen; i++) {
            origin[i] = revIntArr[i];
        }
    }

    /**
     * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
     *
     * @param oldArray
     * @return
     */
    public int[] removeZero(int[] oldArray) {
        int totalLen = oldArray.length;

        int[] tmpArr = new int[totalLen];
        int nonZeroCount = 0;
        for (int i : oldArray) {
            if (i != 0) {
                tmpArr[nonZeroCount++] = i;
            }
        }
        return Arrays.copyOfRange(tmpArr, 0, nonZeroCount);
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
     * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
     *
     * @param array1
     * @param array2
     * @return
     */
    public int[] merge(int[] array1, int[] array2) {
        // 不考虑 寻址
        // 不考虑效率
        int arrLen1 = array1.length;
        int arrLen2 = array2.length;
        int maxLen = arrLen1+arrLen2;
        int[] mergedArr = new int[maxLen];
        int mergedC = 0;
        int i = 0;
        int j = 0;
        while(i<arrLen1 && j<arrLen2){
            int cur1 = array1[i];
            while(j<arrLen2){
                int cur2 = array2[j];
                
                if(cur1<cur2){
                    mergedArr[mergedC++] = cur1;
                    break;
                }else if(cur1==cur2){
                    mergedArr[mergedC++] = cur1;
                    j++;
                    break;
                }else{
                    mergedArr[mergedC++] = cur2;
                }
                
                j++;
            }
            i++;
        }
        // 补齐 没有迭代完的数组，因为这个出现了长度差别
        while(i<arrLen1){
            mergedArr[mergedC++] = array1[i];
            i++;
        }
        while(j<arrLen2){
            mergedArr[mergedC++] = array2[j];
            j++;
        }
        
        return removeZero(mergedArr);
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     *
     * @param oldArray
     * @param size
     * @return
     */
    public int[] grow(int[] oldArray, int size) {
        // 最直接的做法，使用Arrays.copyOf()
        // return Arrays.copyOf(oldArray, oldArray.length+size);
        // 原始做法
        int oldLen = oldArray.length;
        int[] newArray = new int[oldLen + size];
        for (int i = 0; i < oldLen; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
     * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {
        if (max <= 1) {
            return new int[]{};
        } else {
            int[] fibonacciArr = new int[100];
            int growSize = 100;
            fibonacciArr[0] = 1;
            fibonacciArr[1] = 1;
            int curFibNum;
            int curIndex = 2;
            while ((curFibNum = fibonacciArr[curIndex - 1] + fibonacciArr[curIndex - 2]) < max) { 
                if (curIndex == fibonacciArr.length) {
                    fibonacciArr = grow(fibonacciArr, growSize);
                }
                fibonacciArr[curIndex] = curFibNum;
                curIndex++;
            }
            // 清理多余的0
            return removeZero(fibonacciArr);
        }

    }

    /**
     * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int[] primesArr = new int[max];
        // int growSize = 100;
        int Count = 0;
        for(int i=2;i<max;i++){
            if(testIsPrime(i)){
                primesArr[Count++]  = i;
            }
        }
        
        return removeZero(primesArr);
    }

    private boolean testIsPrime(int n){
       if (n <= 3) {
            return n > 1;
        }
       for(int i=2;i<=Math.sqrt(n);i++){
           if(n%i == 0)
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
    public int[] getPerfectNumbers(int max) {
        int[] perfectArr = new int[max];
        // int growSize = 100;
        int Count = 0;
        for(int i=2;i<max;i++){
            if(isPerfectNum(i)){
                perfectArr[Count++]  = i;
            }
        }
        
        return removeZero(perfectArr);
    }
    
    private boolean isPerfectNum(int inNum){
        int sum = 1;
        for(int i = 2;i<inNum;i++){
            if(inNum%i==0){
                sum+=i;
                if(sum>inNum){
                    return false;
                }
            }
        }
        return sum == inNum;
    }
    
    

    /**
     * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
     *
     * @param array
     * @param s
     * @return
     */
    public String join(int[] array, String seperator) {
        StringBuilder sb = new StringBuilder();
        int loopMax = array.length-1;
        for(int i=0;i<loopMax;i++){
            sb.append(array[i]).append('-');
        }
        return sb.append(array[loopMax]).toString();
    }

}
