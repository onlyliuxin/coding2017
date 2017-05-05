package my.collection.linearTest;

import my.collection.linear.MyArrayList;

public class MyArrayListTest {

	public static void main(String[] args) {
		Test();
	}
	
	public static void Test(){
		MyArrayList list = new MyArrayList(5);
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println("size=" + list.size() + "\t" +"get(3)=" + list.get(3));			//abcd
		list.remove(2);																		//abd
		System.out.println(list.toString());
		
		System.out.println("size=" + list.size());					
		list.add(2, "e");																	//abed
		System.out.println(list.toString());
		
		list.add("f");
		list.add("g");																		//abedfg
		System.out.println(list.toString());
		
		list.add(2, "h");																	//abhedfg
		System.out.println(list.toString());
		
		list.remove(0);																		//bhedfg
		System.out.println(list.toString());
		
		System.out.println("get(3)=" + list.get(3));									
	}

}
