package com.my.list;

public class Test {

	public static void main(String[] args) {
		testArrayList();
		

	}
	
	/**
	 * list 测试
	 */
	public static void testArrayList(){
		ArrayList list = new ArrayList();
		list.add("123");
		list.add("123");
		list.add("123");
		list.add("123");
		list.add("123");
		list.add("123");
		
		list.add(1, 111);
		
		list.remove(1);
		
		System.out.println(list.baseArr.length);
		System.out.println(list.size());
		
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			System.out.println(object);
		}
	}

}
