package com.wusa.assignment1;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		System.out.println("hello back");
		
		String[] old = new String[5];
		old[0] = "100";
		old[1] = "101";
		System.arraycopy(old, 0, old, 1, 1);
		System.out.println(Arrays.toString(old));
		
		ArrayList al = new ArrayList();
		
		
	}

}
