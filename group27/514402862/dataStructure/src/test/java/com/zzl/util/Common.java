package com.zzl.util;

import static org.junit.Assert.assertEquals;

public class Common {
	public static String removeTest(List list,int index, String[] str){
		assertEquals(list.size(), 5);
		String result = (String)list.remove(index);
		assertEquals(list.size(), 4);
		
		loop(list,str);
		return result;
	}
	
	public static void loop(List list,String[] str){
		for(int i = 0; i < list.size(); i++){
			assertEquals(list.get(i), str[i]);
		}
	}
	
	public static void loop(Stack s,String[] str){
		int len = s.size();
		for(int i = len - 1; i >= 0; i--){
			assertEquals(s.peek(), str[i]);
			s.pop();
		}
	}
}
