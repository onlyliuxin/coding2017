package com.coding.basic;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
	  LinkedList al = new LinkedList();
	  al.add(0);
	  al.add(1);
	  al.add(2);
	  al.add(3);
	  al.add(4);
	  al.add(5,-1);
	  System.out.println(al.remove(1));
	  System.out.println(al);
	}

}
