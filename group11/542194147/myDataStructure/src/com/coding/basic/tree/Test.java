package com.coding.basic.tree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

	public void helloWorld(String whatYouWant){
		System.out.println(whatYouWant);
	}
//	public static class MyTask extends TimerTask{
//
//		@Override
//		public void run() {
//			try {
//				System.out.println("开始运行,现在时间:"+new Date());
//				Thread.sleep(2000);
//				System.out.println("结束运行,现在时间:"+new Date());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
//	public static void main(String[] args) {
//		
//		try {
//			MyTask myTask1=new MyTask();
//			MyTask myTask2=new MyTask();
//			Timer timer=new Timer("我的线程");
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date1=sdf.parse("2017-05-19 15:07:00");
//			Date date2=sdf.parse("2017-05-19 15:07:10");
//			System.out.println("字符串时间1:"+date1+",当前时间:"+new Date());
//			System.out.println("字符串时间2:"+date2+",当前时间:"+new Date());
//			try {
//				System.out.println(Class.forName("java.util.Timer").getName());
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			timer.schedule(myTask1, date1);
//			timer.schedule(myTask2, date2);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) {
//////		String A="A";
//////		System.out.println(A.hashCode());//hashCode（返回字符串A对应的hashCode码的十进制）
////		Integer a= 1000,b=1000;//自动装箱 Integer a=new Integer(1000)
////
////		Integer c=100,d=100;
////
////		System.out.println(a==b);
////
////		System.out.println(c==d);
////		
////        try {
////        	ArrayList<Integer> arrayList3=new ArrayList<Integer>();  
////            arrayList3.add(1);//这样调用add方法只能存储整形，因为泛型类型的实例为Integer  
////			arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "asd");
////			 for (int i=0;i<arrayList3.size();i++) {  
////		            System.out.println(arrayList3.get(i));  
////		        }  
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//       while(true){
//    	   String abc="abc";
//       }
//	}
	public static void main(String[] args) throws Exception {
		Object test=Class.forName(Test.class.getName()).newInstance();//通过反射创建对象
		Method method= test.getClass().getMethod("helloWorld", String.class);//获取服务方法——helloWorld
		method.invoke(test, "I want become a excellent programmer");//反射调用方法
	}
}
