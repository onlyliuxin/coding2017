package com.dudy.learn01.utils;

import java.util.Arrays;

/**
 * Created by dudy on 2017/3/6.
 * 练习数组的各种排序
 * 参考：http://wiki.jikexueyuan.com/project/java-special-topic/sort.html
 *      http://www.cnblogs.com/liuling/p/2013-7-24-01.html
 *
 * 内排序有可以分为以下几类：

 　　(1)、插入排序：直接插入排序、二分法插入排序、希尔排序。

 　　(2)、选择排序：简单选择排序、堆排序。

 　　(3)、交换排序：冒泡排序、快速排序。

 　　(4)、归并排序

 　　(5)、基数排序
 *
 */
public class ArraySortDemo {


    /**
     * 二分法查找 插入
     * 和 直接插入排序不同的是： 查找 要插入的位置的方式不同
     * 二分法前提是有序的**
     */
    public static void dichotomySort(int src[]){

        for (int i = 0; i< src.length ; i++){
            int temp = src[i];
            int right = i - 1;
            int mid = 0;
            int left = 0;
            while (left <= right){
                mid = (left + right)/2;
                if (temp > src[mid]){
                    left  = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            for (int j = i-1;j>=left ; j--){
                src[j+1] = src[j];
            }

            System.out.println("left = " + left +" ,mid = " + mid + " ,right = " + right);
            src[left] = temp;

        }
    }




    /**
     * 直接插入排序
     * 思想：假定前边是有序地部分， 后边无序的插入到前边部分
     * 可以转变思想: 从后往前遍历， 将有序部分大于当前的值 往后移
     * @param src
     */
    public static  void directInsertSort(int[] src){

        for (int i = 1;i < src.length ; i++){
            // 待插入的元素
            int temp =  src[i];
            int j;
            for ( j = i -1; j >= 0; j--){
                // 大于 temp的往后移动
                if (src[j] > temp){
                    src[j+1] = src[j];
                } else {
                    break;
                }
            }// 此时遍历完 j+1 为要插入的位置
            src[j+1] = temp;
        }

    }



    public static void main(String[] args) {
        int a[] = new int[]{46,89,14,44,90,32,25,67,23};
        // 14,23,25,32,44,46,67,89,90
        //Arrays.sort(a);


        //directInsertSort(a);

        dichotomySort(a);

        for (int i = 0; i< a.length ; i++){
            System.out.print(a[i] + ",");
        }

    }

}
