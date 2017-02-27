package com.coding.basic;

import java.lang.reflect.Field;

public class ArrayListTest {
	private Object[] elementData;
	private static Object[] EMPTY_ELEMENTDATA = {};
	private static Object[] DEMPTY_ELEMENTDATA = {};
	
	public void sss(){
		System.out.println(EMPTY_ELEMENTDATA.equals(elementData));
	}
	public static void main(String[] args) {
		ArrayList l = new ArrayList(0);
		ArrayList l2 = new ArrayList(0);
		Class a = ArrayList.class;
		try {
			Field f =l.getClass().getDeclaredField("elementData");
			Field f2 =l2.getClass().getDeclaredField("elementData");
			System.out.println(f==f2);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
