package com.dudy.learn01.data_structure.base;

import java.util.LinkedList;

import com.dudy.learn01.data_structure.MyIterator;
import com.dudy.learn01.data_structure.list.MyLinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

	MyLinkedList list = new MyLinkedList();

	@Before
	public void  init(){
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
	}

	@After
	public void after(){
		for(MyIterator it = list.iterator(); it.hasNext();){
			System.out.print(it.next() + " ");
		}
	}

	@Test
	public void reverse() throws Exception {
		list.reverse();
	}

	@Test
	public void removeFirstHalf() throws Exception {
		list.removeFirstHalf();
	}

	@Test
	public void remove() throws Exception {
		list.remove(0,2);
	}

	@Test
	public void getElements() throws Exception {

	}

	@Test
	public void subtract() throws Exception {

	}

	@Test
	public void removeDuplicateValues() throws Exception {

	}

	@Test
	public void removeRange() throws Exception {

	}

	@Test
	public void intersection() throws Exception {

	}


	@Test
	public  void iteraterTest(){
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < 10; i++) {
			list.add(i);
		}


		for(MyIterator it = list.iterator(); it.hasNext();){
			System.out.print(it.next() + " ");
		}


	}
	
	@Test
	public void removeTest(){
		MyLinkedList list = new MyLinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		//list.remove(0);
		//list.remove(1);
		list.remove(2);
		System.out.println("--" + list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ",");
		}
	}
	
	
	
	@Test
	public void removeLastTest() {
		MyLinkedList list = new MyLinkedList();
		list.add(1);
		//list.add(2);
		list.removeLast();
		System.out.println("--" + list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ",");
		}
	}
	
	@Test
	public void removeFirstTest() {
		MyLinkedList list = new MyLinkedList();
		list.add(1);
		list.add(2);
		Object first = list.removeFirst();
		System.out.println("--" + list.size() + ",first = " + first);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ",");
		}
	}

	@Test
	public void baseTest() {

		MyLinkedList list = new MyLinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addFirst(0);
		list.addLast("last");
		list.add(3, "s");// 0 1 2 s 3 last
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ",");
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Integer first = list.removeFirst();
	}

}
