package com.github.orajavac.coding2017.jvm;

import java.util.Stack;

/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:2245)
	at java.util.Arrays.copyOf(Arrays.java:2219)
	at java.util.Vector.grow(Vector.java:262)
	at java.util.Vector.ensureCapacityHelper(Vector.java:242)
	at java.util.Vector.addElement(Vector.java:616)
	at java.util.Stack.push(Stack.java:67)
	at com.github.orajavac.coding2017.jvm.OutOfMemoryErrorDemo.f(OutOfMemoryErrorDemo.java:12)
	at com.github.orajavac.coding2017.jvm.OutOfMemoryErrorDemo.main(OutOfMemoryErrorDemo.java:18)

 * @author ora
 *
 */

public class OutOfMemoryErrorDemo {

		
	public static Stack s = new Stack();

	public static void f(){
		for (int i=0;i<99999;i++){
			s.push("a");
		}
	}
	public static void main(String[] args) {
		
		while(true){
			f();
		}

	}

}
