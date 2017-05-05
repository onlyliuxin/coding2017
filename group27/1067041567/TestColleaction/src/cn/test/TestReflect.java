package cn.test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class TestReflect {

	public static void main(String[] args) {
		
		try {
			Class clazz = Class.forName("cn.task2.LoginAction");
			
			Method[] me = clazz.getMethods();
			
			for(Method m:me){
				System.out.println(m);
			}
			
			//Method m1 = clazz.getMethod("setName",String.class);
			Object obj = clazz.newInstance();
			Method m3 = clazz.getMethod("setName",String.class);
			Method mm = clazz.getMethod("getName");
			//m1.invoke(obj, "woshi");
			System.out.println(obj);
			//System.out.println(m1.invoke(obj));
			m3.invoke(obj, "dassdasd-----------------");
			System.out.println(mm.invoke(obj));
			Method execute = clazz.getMethod("execute");
			Type type = execute.getReturnType();
			
			System.out.println("type : "+type);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
