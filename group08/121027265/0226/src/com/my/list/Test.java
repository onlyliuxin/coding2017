package com.my.list;

public class Test {

	public static void main(String[] args) {
		
		testArrayList();
		System.out.println("--------------------------------");
		testLinkedList();
	}
	
	/**
	 * ArrayList 测试
	 */
	public static void testArrayList(){
		System.out.println("ArrayList 测试 --开始");
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
		System.out.println("ArrayList 测试 --结束");
	}
	
	/**
	 * LinkedList 测试
	 */
	public static void testLinkedList(){
		System.out.println("LinkedList 测试 --开始");
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		list.add(1, 111);
		
		list.remove(1);
		
		System.out.println(list.size());
		
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			System.out.println(object);
		}
		System.out.println("LinkedList 测试 --结束");
	}

}
