package com.github.orajavac.coding2017.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Exception in thread "main" java.lang.StackOverflowError
	at com.github.orajavac.coding2017.jvm.StackOverflowErrorDemo.f(StackOverflowErrorDemo.java:26)
	at com.github.orajavac.coding2017.jvm.StackOverflowErrorDemo.f(StackOverflowErrorDemo.java:26)
 *
 */

public class StackOverflowErrorDemo {

	public static void f(){
		f();
	}
	
	public static void main(String[] args) {
        while(true){
        	f();
        }
	}

}
