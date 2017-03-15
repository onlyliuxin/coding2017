package com.easy.codersing.array;

public class PerfectNumber {  
	  
    public static void main(String[] args) {  
  
        System.out.print("1到1000的完数有： ");  
        //fun();// 调用静态方法  
        printComNum(30);
    }  
  
    
    static void printComNum(int n){  
        
        
        
        for(int i = 1; i <= n; i++)  
        {  
            int sum = 0;  
            for(int j = 1; j < i; j++)  
            {  
                if(i % j == 0)  
                {  
                    sum = sum + j;    
                }     
            }  
            if(sum == i)  
            {  
                System.out.println(i);  
            }   
        }  
          
    }  
    /* 
     * 要在main（）方法中调用fun()方法， 那么fun（）方法必须为static型的 
     */  
    public static void fun() {  
  
        for (int i = 1; i <= 30; i++) {  
  
            int temp = 0;// 定义因子之和变量  
  
            for (int n = 1; n < i / 2 + 1; n++) {  
                if (i % n == 0) {  
                    temp += n;// 能被整除的除数则被加到temp中  
                }  
            }  
            if (temp == i) {// 如果因子之和与原数相等的话，说明是完数  
                System.out.print(i + " ");// 输出完数  
            }  
        }  
    }  
} 
