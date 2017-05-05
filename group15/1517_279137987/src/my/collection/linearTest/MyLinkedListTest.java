package my.collection.linearTest;

import my.collection.linear.MyLinkedList;

public class MyLinkedListTest {

	public static void main(String[] args) {
		MyLinkedList mll = new MyLinkedList();
		mll.add("a");
		mll.add("b");
		mll.add("c");				//abc
		mll.add(1, "o");			//aobc
		mll.add(2, "v");			//aovbc
		mll.add("x");				//aovbcx
		mll.remove(2);				//aobcx
		mll.remove(0);				//obcx
		mll.addFirst("y");			//yobcx
		mll.addLast("m");			//yobcxm
		mll.removeFirst();			//obcxm
		mll.removeLast();			//obcx
		
		System.out.println("size = " + mll.size());
		
		for(int i=0; i<mll.size(); i++){
			System.out.print(mll.get(i) + "\t");
		}
		
		System.out.println(mll.toString());

	}

}
