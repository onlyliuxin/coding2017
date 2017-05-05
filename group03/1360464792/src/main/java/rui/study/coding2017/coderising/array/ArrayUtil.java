package rui.study.coding2017.coderising.array;

import java.util.Arrays;

/**
 * 创建于 2017-03-01.
 *
 * @author 赵睿
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin
     * @return
     */
    public int[] reverseArray(int[] origin){
        int length=origin.length;

        int[] result=new int[length];
        int j=0;
        for (int i = length-1; i >=0 ; i--) {
            result[j]=origin[i];
            j++;
        }
        return result;
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray
     * @return
     */

    public int[] removeZero(int[] oldArray){
        int length=oldArray.length;
        int notZero=0;
        int[] temArray=new int[length];
        for (int i = 0; i <length ; i++) {
            if(oldArray[i]!=0){
                temArray[notZero]=oldArray[i];
                notZero++;
            }
        }
        temArray=Arrays.copyOf(temArray,notZero);
        return temArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */

    public int[] merge(int[] array1, int[] array2){
        int tempSize=0;
        int i=0;
        int j=0;
        boolean flag=false;
        int tempArr[]=new int[array1.length+array2.length];
        while(!flag){
            if(array1[i]>array2[j]){
                tempArr[tempSize]=array2[j];
                j++;
            }else if(array1[i]==array2[j]){
                tempArr[tempSize]=array1[i];
                i++;j++;
            }else {
                tempArr[tempSize]=array1[i];
                i++;
            }
            tempSize++;
            flag=j>array2.length-1||i>array1.length-1;
        }
        if(i<=j){
            for (; i < array1.length ; i++) {
                tempArr[tempSize]=array1[i];
                tempSize++;
            }
        }else{
            for (; j < array2.length ; i++) {
                tempArr[tempSize]=array2[i];
                tempSize++;
            }
        }
        return Arrays.copyOf(tempArr,tempSize);
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
        return Arrays.copyOf(oldArray,oldArray.length+size);
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     * @param max
     * @return
     */
    public int[] fibonacci(int max){
        int[] empty={};
        int tempSize=0;
        int temp[]=new int[max];

        if(max<=1){
            return empty;
        }else if(max==2){
            temp[0]=1;
            temp[1]=1;
            return temp;
        }else{
            temp[0]=1;
            temp[1]=1;
            tempSize=2;
        }
        boolean flag=false;
        while(!flag){
            int tempMax=temp[tempSize-2]+temp[tempSize-1];
            if(tempMax>max){
                flag=true;
            }else{
                temp[tempSize++]=tempMax;
            }
        }
        return Arrays.copyOf(temp,tempSize);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
        int temp[]=new int[max];
        int tempSize=0;
        for (int i = 0; i < max; i++) {
            if(isPrimes(i)){
                temp[tempSize++]=i;
            }
        }
        return Arrays.copyOf(temp,tempSize);
    }

    private boolean isPrimes(int num){
        if(num<2) return false;
        if(num==2) return true;
        if(num==3) return true;
        for (int i = 2; i *i<=num; i++) {
            if(num%i==0) {
                 return false;
            }
        }
        return true;
    }


    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        int temp[] =new int[max];
        int tempSize=0;

        for (int i = 0; i < max; i++) {
            if(isPerfectNumber(i)){
                temp[tempSize++]=i;
            }
        }
        return Arrays.copyOf(temp,tempSize);
    }

    private boolean isPerfectNumber(int num){
        if(num<1)return false;
        if(num==1)return true;
        int temp[] =new int[num];
        int tempSize=0;
        temp[tempSize++]=1;
        for (int i = 2; i < num; i++) {
            if(num%i==0){
                temp[tempSize++]=i;
            }
        }

        int add=0;
        for (int i = 0; i < tempSize; i++) {
            add+=temp[i];
        }
        if(add==num)return true;
        return false;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     * @param array
     * @param
     * @return
     */
    public String join(int[] array, String seperator){
        StringBuilder stringBuilder=new StringBuilder();

        for (int i = 0; i < array.length-1; i++) {
            stringBuilder.append(array[i])
                    .append(seperator);
        }
        stringBuilder.append(array[array.length-1]);
        return stringBuilder.toString();
    }
}