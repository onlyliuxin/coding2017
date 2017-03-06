package com.coding.basic;

public class MainTest {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add("this is the fifth");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(3));
		
		while(list.hasNext()){
			System.out.println(list.next());
		}
	}

}
