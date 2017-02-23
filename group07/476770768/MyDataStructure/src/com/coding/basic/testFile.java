package com.coding.basic;

public class testFile {

	public static void main(String[] args) {
		MyLinkedList mll = new MyLinkedList();
		mll.add(new Integer(5));
		mll.add(new Integer(2));
		mll.add(new Integer(3));
		mll.add(new Integer(4));
		System.out.println(mll);
		MyIterator mIt = mll.iterator();
		while(mIt.hasNext()){
			System.out.println(mIt.next());
		}
		mll.remove(3);
		System.out.println(mll);
		
		
		
	}

}
