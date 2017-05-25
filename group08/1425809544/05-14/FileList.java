package com.zhuoyue.scheduleplan.domain;

import java.io.File;

/**
 * 给定一个目录，递归的列出下面所有的子目录和文件
 *
 * @author xyy
 * @create 2017-05-09 19:21
 **/
public class FileList {


    public static void list(File file) {

        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isDirectory()) {
                            System.out.println(files[i].getPath());
                        }
                        list(files[i]);
                    }
                }
            } else {
                System.out.println(file);
            }
        }

    }


    /**
     * @Author xuyangyang
     * @Describe 冒泡排序
     * @Date 2017/5/17
     * @Params
     * @Return
     */
    public static int[] bubbleSort1(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }


    /**
     * @Author xuyangyang
     * @Describe 改进版本
     * N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次，所以可以用双重循环语句，外层控制循环多少趟，内层控制每一趟的循环次数，
     * @Date 2017/5/17
     * @Params
     * @Return
     */
    public static int[] bubbleSort2(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }


    /**
     * @Author xuyangyang
     * @Describe 选择排序
     * @Date 2017/5/17
     * @Params
     * @Return
     */
    public static int[] selectSort1(int[] array) {

        for (int i = 0; i < array.length - 1; i++) { //第i趟排序
            int k = i;
            for (int j = k + 1; j < array.length; j++) { //选最小的记录
                if (array[j] < array[k]) {
                    k = j;  //记下目前找到的最小值所在的位置;
                }
            }
            //在内层循环结束,找到本轮循环的最小的数以后，再进行交换
            if (i != k) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }

        return array;
    }

	
	
	//阶乘函数
	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		}
		return factorial(n - 1) * n;
	}

	//斐波那契数列
	public static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);


	}

	//判断回文字符串
	public static int fun(int low, int high, String str, int length) {
		if (length == 0 || length == 1) {
			return 1;
		}
		String a = str.substring(low, low + 1);
		String b = str.substring(high, high + 1);
		if (!a.equals(b)) {
			return 0;
		}
		return fun(low + 1, high - 1, str, length - 2);
	}


	//字符串翻转：将字符串 test 翻转，变为 tset
	public static String reversal(String str) {

		return null;
	}
	 
	
    public static void main(String[] args) {
        String fileName = "F:\\Shadowsocks-3.4.3";
        File file = new File(fileName);
        list(file);

        System.out.println("---------------我是调皮的分割线!---------------------");
        int[] arrays = {6, 3, 8, 2, 9, 1};
        int[] a = bubbleSort1(arrays);
        for (int i : a) {
            System.out.println(i);
        }
        System.out.println("---------------我是调皮的分割线!---------------------");

        int[] b = bubbleSort2(arrays);
        for (int i : b) {
            System.out.println(i);
        }
        System.out.println("---------------我是调皮的分割线!---------------------");
        int[] c = selectSort1(arrays);
        for (int i : c) {
            System.out.println(i);
        }
		
		
		int factorialSum = factorial(10);
		System.out.println(factorialSum);

		int fibonacciSum = fibonacci(10);
		System.out.println(fibonacciSum);

		String str = "aaabbaaa";
		int len = str.length();
		int funSum = fun(0, len - 1, str, len);
		System.out.println(funSum);
    }
	
	
	

}



