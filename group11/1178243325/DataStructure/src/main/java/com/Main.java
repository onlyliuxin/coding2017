package com;

import com.coding.basic.LinkedList;
import com.coding.basic.Iterator;
public class Main {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();

		for(int i = 0; i < 10; i++)
			list.add(i);
		list.remove(2, 5);
		Iterator	iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
