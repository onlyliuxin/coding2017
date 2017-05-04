package com.coding.basic;

import java.lang.reflect.Method;

public class teest {
	 public static void main(String[] args) {
	        Class<?> herosClass = Heros.class;
	        try {
	            Method m1 = herosClass.getMethod("setName",String.class);
	            Method m3 = herosClass.getMethod("setCamp",int.class);
	            Method m2 = herosClass.getMethod("getName");
	            
	          
	            Object userInfo = herosClass.newInstance();
	            System.out.println("调用构造函数："+userInfo);
	            m1.invoke(userInfo,"影魔");
	            m3.invoke(userInfo, 1);
	            System.out.println("调用set方法："+userInfo);
	            System.out.println("调用get方法："+m2.invoke(userInfo));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
