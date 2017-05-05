package coderising.array;

import java.util.ArrayList;

/**
 * 
 * @author Wayss
 * 2017-03-01
 *
 */

public class ArrayUtil {
    
    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin
     * @return
     */
    public int[] reverseArray(int[] origin){
        int length = origin.length;
        int loopNumber = length/2;
        int temp;
        for(int i = 0; i < loopNumber; i++){
            temp = origin[i];
            origin[i] = origin[length - 1 - i];
            origin[length -1 -i] = temp;
        }
        return origin;
    }
    
    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
     * {1,3,4,5,6,6,5,4,7,6,7,5}  
     * @param oldArray
     * @return
     */
    
    public int[] removeZero(int[] oldArray){
        ArrayList list = new ArrayList();
        for(int i = 0,j = 0; i < oldArray.length; i++){
            if(oldArray[i] != 0){
                list.add(oldArray[i]);
            }
        }
        Object[] objectArray = list.toArray();
        int[] newArray = new int[objectArray.length];
        for(int i = 0; i < objectArray.length; i++){
            newArray[i] = (int)objectArray[i];
        }
        return newArray;
    }
    
    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */
    
    public int[] merge(int[] array1, int[] array2){
        int [] newArray = new int[array1.length + array2.length];
        int i = 0,j = 0,k = 0;
        //只知道循环的终止条件是，数组1遍历完，同时数组2遍历完
        while(i < array1.length){
            while(j < array2.length){
                if(array1[i] < array2[j]){
                    newArray[k++] = array1[i];
                    i++;
                }else if(array1[i] == array2[j]){
                    //这种比较相等方法中，array1和array2两个数组中，本身不能有重复数字，否则新的数组仍有重复.
                    newArray[k++] = array1[i];
                    i++;
                    j++;
                }else if(array1[i] > array2[j]){
                    newArray[k++] = array2[j];
                    j++;
                }
            }
            //这种情况是为了防止死循环，即，当数组1未遍历完，数组2遍历完时，内循环不会进入，外循环也不会出去时，
            //此时，数组1的最后一个数字肯定比数组2大，并且，这种情况不会存在数组2的最后一个数字比数组1大时。
            newArray[k++] = array1[i++];
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
        for(int i = 0; i < oldArray.length; i++){
            newArray[i] = oldArray[i];
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
    public int[] fibonacci(int max){
        if(1 == max){
            return new int[0];
        }
        if(max == 2){
            return new int[]{1,1};
        }
        int [] result = new int[]{1,1,2};
        //i表示数组中最后一个数字的下标
        int i = 2;
        //斐波那契数列的最后一个数
        int lastNumber = 3;
        while(lastNumber < max){
            lastNumber = result[i] + result[i-1];
            //添加前，判断数组是否需要扩大
            if(result.length == i+1){
                result = grow(result, 1);
            }
            result[i++] = lastNumber;
        }
        return result;
    }
    
    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
        //index为result最后的下标
        int index = 0;
        int result[] = new int[0];
        for(int i = 2; i < max; i++){
            for(int j = 2; j < i; j++){
                //判断内循环j是否是外循环i的质数
                if(i % j == 0){
                    break;
                }
                if(result.length == index+1){
                    result = grow(result, 1);
                }
                result[index++] = i;
            }
        }
        return result;
    }
    
    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        //index为result最后的下标
        int index = 0;
        int result[] = new int[0];
        for(int i = 0; i < max; i++){
            int sum = 0;
            for(int j = 0; j < i; j++){
                //j如果可以被i整除，代表j是i的因子
                if(i % j == 0){
                    sum += j;
                }
            }
            //sum是因子之和，若等于i本身，就是“完数”
            if(sum == i){
                if(result.length == index+1){
                    result = grow(result, 1);
                }
                result[index++] = i;
            }
        }
        
        return result;
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
        String result = "";
        for(int i = 0; i < array.length; i++){
            if(i == array.length -1 ){
                result += array[i];
            }else{
                result += array[i] + "-";
            }
        }
        return result;
    }
    

}
