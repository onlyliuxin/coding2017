package week1.collectiontest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import week1.collections.Iterator;
import week1.collections.LinkedList;

public class LinkedListTest {
	LinkedList list;
	@Before
	public void init(){
		list = new LinkedList();
		for(int i=1;i<=10;i++){
			list.add(i);
		}
	}
	
	@Test
	public void test1(){
		for(int i=1;i<=10;i++){
			assertEquals(i, list.get(i-1));
		}
	}
	
	@Test
	public void test2(){
		list.add(0,4.5);
		assertEquals(list.get(0), 4.5);
		assertEquals(list.get(1), 1);
	}
	
	@Test
	public void test3(){
		assertEquals(list.remove(0), 1);
		assertEquals(list.get(5), 7);
		assertEquals(list.remove(4), 6);
		System.out.println(list.size());
	}
	
	@Test
	public void test4(){
		list.addFirst(0.5);
		list.addLast(10.5);
		assertEquals(list.get(0),0.5);
		assertEquals(list.get(11), 10.5);
	}
	
	@Test
	public void test5(){
		assertEquals(list.remove(9), 10);
		list.addLast(10.5);
		assertEquals(list.get(9), 10.5);
		list.addFirst(1.5);
		assertEquals(list.get(0), 1.5);
	}
	
	@Test
	public void test6(){
		assertEquals(list.removeFirst(), 1);
		assertEquals(list.removeLast(),10);
		list.addFirst(55);
		list.addLast(100);
		assertEquals(list.get(0), 55);
		assertEquals(list.get(9), 100);
	}
	
	@Test
	public void test7(){
		Iterator it = list.iterator();
		while(it.hasNext()){
			for(int i=1;i<=10;i++){
				assertEquals(it.next(), i);
			}
		}
	}
	
	@Test
	public void test8(){
		for(int i=1;i<=10;i++){
			assertEquals(list.removeFirst(), i);
			System.out.println(list.size());
		}
	}
}
