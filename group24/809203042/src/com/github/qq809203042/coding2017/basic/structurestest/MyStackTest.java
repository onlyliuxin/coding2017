package com.github.qq809203042.coding2017.basic.structurestest;

import com.github.qq809203042.coding2017.basic.structures.MyStack;

public class MyStackTest {

	public static void main(String[] args) {
		MyStack ms = new MyStack();
		ms.push(new String("yi"));
		ms.push(new String("er"));
		ms.push(new String("san"));
		ms.push(new String("si"));
		ms.push(new String("wu"));
		ms.push(new String("liu"));
		
		System.out.println(ms);
		ms.pop();
		System.out.println(ms);
		ms.pop();
		System.out.println(ms);
		System.out.println(ms.peek());
		System.out.println(ms.size());
	}

}
