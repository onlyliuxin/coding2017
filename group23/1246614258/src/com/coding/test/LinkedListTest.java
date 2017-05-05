package com.coding.test;
import com.coding.datastructs.LinkedList;


public class LinkedListTest {

	/**
	 * <p>Description:</p>
	 * @param args
	 * @author:Wilson huang
	 * @date 2017-3-12ÏÂÎç1:45:58
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.removeLast();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
			
		}
	}

}
