package edu.coerscnu.client;

import edu.coerscnu.basic.Iterator;
import edu.coerscnu.basic.list.LinkedList.MyLinkedList;

public class Client {

	public static void main(String[] args) {
		MyLinkedList<Integer> linkedList = new MyLinkedList<>();
		for (int i = 0; i < 8; i++) {
			linkedList.add(i);
		}
		Iterator<Integer> iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
