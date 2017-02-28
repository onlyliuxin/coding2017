package com.coding.basic;

public class testarraylist {
	public static void main(String[] args) {
		ArrayList abc = new ArrayList();
		String  i = "aaa";
		String  y = "bbb";
		Object o = new Object();
		
		
		abc.add(i);
		System.out.println(abc.get(0));
	//	System.out.println(abc.get(1));
		abc.add(y);
		System.out.println(abc.get(0));
		System.out.println(abc.get(1));
		abc.remove(1);
		System.out.println(abc.get(0));
		System.out.println(abc.get(1));
	}

}
