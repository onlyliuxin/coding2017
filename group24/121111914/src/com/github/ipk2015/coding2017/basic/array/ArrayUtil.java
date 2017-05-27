package com.github.ipk2015.coding2017.basic.array;



import java.util.ArrayList;
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
        int temp=0;
        for(int i=0;i<origin.length/2;i++){
            temp=origin[i];
            origin[i]=origin[origin.length-i-1];
            origin[origin.length-i-1]=temp;
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
        int[] newArray=new int[oldArray.length];
        int size=0;
        for(int i=0;i<oldArray.length;i++){
            if(oldArray[i]!=0){
                newArray[size]=oldArray[i];
                size++;
            }
        }
        newArray= Arrays.copyOf(newArray,size);
        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */
    //两种思路：一种是先排序，后删除重复元素；一种是直接按顺序插好；
    public int[] merge(int[] array1, int[] array2){
        checkArraySort(array1);
        checkArraySort(array2);
        ArrayList<Integer> list= new ArrayList();
        for(int i=0;i<array1.length;i++){
            list.add(array1[i]);
        }
        int size;
        for(int i=0;i<array2.length;i++){
        	size=list.size();
            for(int j=0;j<size;j++){
                if(array2[i]<list.get(j)){
                    list.add(j,array2[i]);
                    break;
                }
                if(array2[i]==list.get(j)){
                    break;
                }
                if(j==size-1){
                	list.add(array2[i]);
                }
            }
        }
        int[] tempArray=new int[list.size()];
        for(int i=0;i<tempArray.length;i++){
            tempArray[i]=list.get(i);
        }
        return  tempArray;
    }
    /*
    * 检查数组是否按从小到大排序，且无重复元素
    * */
    private void checkArraySort(int[] array){
        if(array.length<2){
            return;
        }
        for(int i=0;i<array.length-1;i++){
            if(array[i]>=array[i+1]){
                throw new RuntimeException("不是排序好的数组");
            }
        }
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
        int[] newArray=new int[oldArray.length+size];
        for(int i=0;i<oldArray.length;i++){
            newArray[i]=oldArray[i];
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
        if(max<=1){
            return new int[0];
        }
        ArrayList<Integer> list=new ArrayList();
        list.add(1);
        list.add(1);
        int size=2;
        int lastElement=2;
        while(lastElement<max){
            list.add(lastElement);
            size++;
            lastElement=list.get(size-1)+list.get(size-2);
        }
        int[] array=new int[size];
        for(int i=0;i<array.length;i++){
            array[i]=list.get(i);
        }
        return array;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
        if(max<3){
            return new int[0];
        }
        ArrayList<Integer> list=new ArrayList();
        list.add(2);
        for(int i=3;i<max;i++){
            if(isPrimes(i)){
                list.add(i);
            }
        }
        int[] array=new int[list.size()];
        for(int i=0;i<array.length;i++){
            array[i]=list.get(i);
        }
        return array;
    }
    private boolean isPrimes(int number){
        if(number<2){
            return false;
        }
        for(int i=2;i<number;i++){
            if(number%i ==0){
                return false;
            }
        }
        return true;
    }
    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3，
     * 10000以内的完全数：6，28，496，8128，下一个是33550336....
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        if(max<2){
            return new int[0];
        }
        ArrayList<Integer> list=new ArrayList();
        for(int i=2;i<max;i++){
            if(isPerfectNumbers(i)){
                list.add(i);
            }
        }
        int[] array=new int[list.size()];
        for(int i=0;i<array.length;i++){
            array[i]=list.get(i);
        }
        return array;
    }

    private boolean isPerfectNumbers(int number){
        int sum=1;
        for(int i=2;i<number;i++){
            if(number%i==0){
                sum=sum+i;
            }
        }
        return sum==number;
    }
    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     * @param array
     * @param separator
     * @return
     */
    public String join(int[] array, String separator){
        int size=array.length;
        if(size==0){
            return "";
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(array[0]+"");
        for(int i=1;i<size;i++){
            stringBuilder.append(separator+array[i]);
        }
        return stringBuilder.toString();
    }
    
}
