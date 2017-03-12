package com.coding.basic.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList<String> list = new LinkedList<String>();
		  list.add("First");
		  list.add("Second");
		  list.add("Thrid");
		  for(int i = 0; i < list.size(); i++) {
			  System.out.print(list.get(i)+ " ");
		  } 	  
			  
		  }
}
