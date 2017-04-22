package com.ralf.linkedlist;

import BasicData.MyIterator;

public class LinkedListTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList<Integer> list = new MyLinkedList<>();
		
		MyLinkedList<Integer> listB = new MyLinkedList<>();
		MyLinkedList<Integer> listC = new MyLinkedList<>();
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(17);
		list.add(18);

		listB.add(10);
		listB.add(12);
		listB.add(14);
		listB.add(15);
		listB.add(18);
		
		
		listC = (MyLinkedList<Integer>) list.intersection(listB);


		System.out.println(listC.size());
		
		MyIterator<Integer> iterator = listC.iterator();
		while(iterator.hasNext()){
			Integer integer = iterator.Next();
			System.out.println(integer);
		}


	}

}
