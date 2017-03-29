package com.coding.test;
import com.coding.datastructs.Stack;


public class StackTest {

	/**
	 * <p>Description:</p>
	 * @param args
	 * @author:Wilson huang
	 * @date 2017-3-12обнГ2:34:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack a = new Stack();
		a.push("a");
		a.push("b");
		a.push("c");
		System.out.println(a.isEmpty());
		System.out.println(a.peek());
		System.out.println(a.pop());

	}

}
