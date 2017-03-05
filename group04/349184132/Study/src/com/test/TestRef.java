package com.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRef {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, InvocationTargetException {
		Class c = Class.forName("com.test.Person");
		Object o = c.newInstance();
		Method[] methods = c.getMethods();
//		for(Method m : methods){            
//			System.out.println(m.getName());  //打印父类方法和子类方法
//		}
		Method[] methods2 = c.getDeclaredMethods(); //自己本类的方法
//		for(Method m: methods2){
//			System.out.println(m.getName());
//		}
		Method m = c.getMethod("print", null); //方法名字 获取方法
//		System.out.println(m.getName());
		
//		Field[] fields = c.getFields();
//		for (Field field : fields) {
//			System.out.println(field.getName());
//		}
		Field[] fields2 = c.getDeclaredFields(); //本类的字段
//		for (Field field : fields2) {
//			System.out.println(field.getName());
//		}
		Field f = c.getDeclaredField("age");
		Field f2 = c.getDeclaredField("name");
		
		f.setAccessible(true);
		f2.setAccessible(true);
		// java.lang.NoSuchFieldException
		f.set(o, 12);
		f2.set(o, "wang");
//		System.out.println(f.get(o));
//		System.out.println(f2.get(o));
		Method m2 = c.getDeclaredMethod("setAge", int.class);
		m2.invoke(o, 12);
		Field f3 = c.getDeclaredField("age");
		f3.setAccessible(true);
		System.out.println(f3.get(o));
		
		
	}
}
